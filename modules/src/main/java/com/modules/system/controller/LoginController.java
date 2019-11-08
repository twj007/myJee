package com.modules.system.controller;

import com.common.constant.CommonConstants;
import com.common.model.vo.system.LoginUser;
import com.common.utils.EncryptUtils;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/31
 **/
@RestController
@RequestMapping("/sys")
public class LoginController {

    private static final String ADMIN_USERNAME = "jien";

    private static final String ADMIN_PASSWORD = "123456";

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    public ResultBody<String> login(LoginUser user, HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader(CommonConstants.X_ACCESS_TOKEN);
        if(token != null && EncryptUtils.verify(token)){
            //刷新token并且返回

            return Results.SUCCESS.result("当前用户已登陆", null);
        }
        if(ADMIN_USERNAME.equals(user.getUsername()) && ADMIN_PASSWORD.equals(user.getPassword())){
            //颁发token
            String accessToken = EncryptUtils.encode(ADMIN_PASSWORD, user.getUsername());
            response.setHeader(CommonConstants.X_ACCESS_TOKEN, accessToken);
            redisTemplate.opsForValue().set(CommonConstants.PREFIX_USER_TOKEN+user.getUsername(), accessToken, CommonConstants.EXPIRE_TIME, TimeUnit.MILLISECONDS);
            return Results.SUCCESS.result("登陆成功", null);
        }else{
            return Results.SUCCESS.result("用户名密码错误", null);
        }
    }
}
