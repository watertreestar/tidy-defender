package com.ranger.defender.auth.impl;

import com.ranger.defender.auth.AuthenticationToken;

/**
 * @Author ranger
 * @Date 2020/1/19 11:10
 **/
public class SimpleAuthenticationToken implements AuthenticationToken {

    private String username;
    private String password;
    private boolean rememberMe;


    public SimpleAuthenticationToken(String username) {
        this.username = username;
    }

    public SimpleAuthenticationToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SimpleAuthenticationToken(String username, String password, boolean rememberMe) {
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public String password(){
        return this.password;
    }

    @Override
    public boolean isRememberMe(){
        return this.rememberMe;
    }


}
