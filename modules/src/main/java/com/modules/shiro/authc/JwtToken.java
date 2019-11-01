package com.modules.shiro.authc;

import org.apache.shiro.authc.AuthenticationToken;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/30
 **/
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
