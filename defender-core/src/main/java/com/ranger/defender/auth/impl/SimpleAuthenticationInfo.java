package com.ranger.defender.auth.impl;

import com.ranger.defender.auth.AuthenticationInfo;

import java.util.Map;

/**
 * @Author ranger
 * @Date 2020/1/19 11:14
 **/
public class SimpleAuthenticationInfo implements AuthenticationInfo {

    private String username;
    private String password;
    private Map<String,Object> claims;
    private Object payload;


    public SimpleAuthenticationInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SimpleAuthenticationInfo(String username, String password, Map<String, Object> claims, Object payload) {
        this.username = username;
        this.password = password;
        this.claims = claims;
        this.payload = payload;
    }

    public SimpleAuthenticationInfo(String username, String password, Map<String, Object> claims) {
        this.username = username;
        this.password = password;
        this.claims = claims;
    }

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public Object payload(){
        return this.payload;
    }

    @Override
    public Map<String,Object> claims(){
        return this.claims;
    }



}
