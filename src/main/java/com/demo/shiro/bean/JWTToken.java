package com.demo.shiro.bean;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Auther: ZHANG.HAO
 * @Date: 2019-02-21 14:45
 * @Description:
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
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