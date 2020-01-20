package com.modules.system.controller;

import com.common.constant.CommonConstants;
import com.common.model.vo.system.LoginUser;
import com.common.model.vo.system.SysUser;
import com.common.utils.EncryptUtils;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.jee.service.system.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/31
 **/
@RestController
@RequestMapping("/sys/login")
@Api("登陆模块")
@Slf4j
public class LoginController {

    private static final String ADMIN_USERNAME = "jien";

    private static final String ADMIN_PASSWORD = "123456";

    private static final String PREFIX = "sys/";

    private static final String PHONE_PATTERN = "/^1([38]\\d|4[5-9]|5[0-35-9]|6[56]|7[0-8]|9[189])\\d{8}$/";

    private static final String RANDOM_KEY = "verify";

    private static final Long MAX_VALUE = 9999L;

    private static final Long MIN_VALUE = 1000L;


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ISysUserService userService;

    @RequestMapping("/getRoleByUser")
    @ApiOperation("通过用户获取角色")
    @ApiImplicitParam(name = "username")
    public ResultBody getRoleByUser(String username){

        Set<String> roleSet = userService.getUserRolesSet(username);
        if(roleSet == null || roleSet.size() == 0){
            return Results.BAD__REQUEST.result("该用户不存在角色", null);
        }
        List<String> roles = new ArrayList<>();
        roles.addAll(roleSet);
        return Results.SUCCESS.result("success", roleSet);
    }

    @RequestMapping("/getUserByRole")
    @ApiOperation("工作流程中通过角色获取用户")
    @ApiImplicitParam(name = "taskId")
    public ResultBody getUserByRole(String taskId){

        List<SysUser> users = userService.findUserByRole(taskId);
        if(users == null || users.size() == 0){
            return Results.BAD__REQUEST.result("查询下个处理人为空", null);
        }
        return Results.SUCCESS.result("success", users);
    }

    @RequestMapping("/page/login")
    @ApiOperation("登陆页")
    public ModelAndView loginPage(ModelAndView modelAndView){
        modelAndView.setViewName(PREFIX + "login");
        return modelAndView;
    }


    @RequestMapping("/checkUserExists/{username}")
    @ApiOperation("检查该用户名下是否存在")
    public ResultBody checkUserExists(@PathVariable("username")String username){
        int count = userService.checkUserExists(username);
        if(count > 0){
            return Results.USER_EXISTS.result(null);
        } else{
            return Results.SUCCESS.result("success", null);
        }
    }


    @RequestMapping("/login")
    @ApiOperation("登陆校验")
    public ResultBody<String> login(LoginUser user, HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader(CommonConstants.X_ACCESS_TOKEN);
        if(token != null && EncryptUtils.verify(token)){
            //刷新token并且返回
            return Results.SUCCESS.result("当前用户已登陆", null);
        }
        LoginUser u;
        if(ADMIN_USERNAME.equals(user.getUsername()) && ADMIN_PASSWORD.equals(user.getPassword())){
            //颁发token
            String accessToken = EncryptUtils.encode(ADMIN_PASSWORD, user.getUsername());
            response.setHeader(CommonConstants.X_ACCESS_TOKEN, accessToken);
            redisTemplate.opsForValue().set(CommonConstants.PREFIX_USER_TOKEN+user.getUsername(), accessToken, CommonConstants.EXPIRE_TIME, TimeUnit.MILLISECONDS);
            return Results.SUCCESS.result("登陆成功", null);
        }else if((u = userService.login(user)) != null){
            String accessToken = EncryptUtils.encode(u.getPassword(), u.getUsername());
            response.setHeader(CommonConstants.X_ACCESS_TOKEN, accessToken);
            redisTemplate.opsForValue().set(CommonConstants.PREFIX_USER_TOKEN+user.getUsername(), accessToken, CommonConstants.EXPIRE_TIME, TimeUnit.MILLISECONDS);
            return Results.SUCCESS.result("登陆成功", null);
        }else{
            return Results.BAD__REQUEST.result("用户名密码错误", null);
        }
    }

    @RequestMapping("/sms/login/{phone}")
    @ApiOperation("手机号验证码校验")
    public ResultBody loginByPhone(@PathVariable("phone")String phone, HttpServletRequest request){
        if(!phone.matches(PHONE_PATTERN)){
            return Results.INVALIDATE.result(null);
        }else{
            // 调用api 发送短信
            String validateCode = (String) redisTemplate.execute(new RedisCallback() {
                @SuppressWarnings("Duplicates")
                @Override
                public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    String res = new String(redisConnection.sRandMember(RANDOM_KEY.getBytes()));
                    if(res == null){
                        long x = MIN_VALUE;
                        while(x < MAX_VALUE){
                            redisConnection.sAdd(RANDOM_KEY.getBytes(), String.valueOf(x).getBytes());
                            x++;
                        }
                        res = new String(redisConnection.sRandMember(RANDOM_KEY.getBytes()));
                    }
                    return res;
                }
            });
            request.getSession().setAttribute("sms_validate_code", validateCode);
            request.getSession().setAttribute("phone", phone);
            return Results.SUCCESS.result("发送成功", null);
        }
    }

    @RequestMapping("/sms/login/validate")
    @ApiOperation("验证验证码")
    public ResultBody validateVerifyCode(String validateCode, HttpServletRequest request){
        if(StringUtils.isEmpty(validateCode)){
            log.warn("【sms验证码验证】传入的校验验证码为空");
            return Results.INVALIDATE.result(null);
        }
        String storedCode = (String) request.getSession().getAttribute(RANDOM_KEY);
        if(StringUtils.isEmpty(storedCode)){
            log.warn("【sms验证码验证】session中不存在验证码");
            return Results.INVALIDATE.result("");
        }
        if(storedCode.equals(validateCode)){
            // 颁发 token
            String phone = (String) request.getSession().getAttribute("phone");
            SysUser user = userService.getUserByPhone(phone);
            String token = EncryptUtils.encode(user.getPassword(), user.getUsername());
        }
        return Results.INVALIDATE.result(null);
    }
}
