package com.modules.system.controller;

import com.common.constant.CommonConstants;
import com.common.model.vo.system.LoginUser;
import com.common.model.vo.system.SysUser;
import com.common.utils.EncryptUtils;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
public class LoginController {

    private static final String ADMIN_USERNAME = "jien";

    private static final String ADMIN_PASSWORD = "123456";

    private static final String PREFIX = "/sys/";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ISysUserService userService;

    @RequestMapping("/getRoleByUser")
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
    public ResultBody getUserByRole(String taskId){

        List<SysUser> users = userService.findUserByRole(taskId);
        if(users == null || users.size() == 0){
            return Results.BAD__REQUEST.result("查询下个处理人为空", null);
        }
        return Results.SUCCESS.result("success", users);
    }

    @RequestMapping("/page/login")
    public ModelAndView logePage(ModelAndView modelAndView){
        modelAndView.setViewName(PREFIX + "login");
        return modelAndView;
    }


    @RequestMapping("/checkUserExists")
    public ResultBody checkUserExists(){

        return null;
    }


    @RequestMapping("/login")
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
            String accessToken = EncryptUtils.encode(user.getPassword(), user.getUsername());
            response.setHeader(CommonConstants.X_ACCESS_TOKEN, accessToken);
            redisTemplate.opsForValue().set(CommonConstants.PREFIX_USER_TOKEN+user.getUsername(), accessToken, CommonConstants.EXPIRE_TIME, TimeUnit.MILLISECONDS);
            return Results.SUCCESS.result("登陆成功", null);
        }else{
            return Results.BAD__REQUEST.result("用户名密码错误", null);
        }
    }
}
