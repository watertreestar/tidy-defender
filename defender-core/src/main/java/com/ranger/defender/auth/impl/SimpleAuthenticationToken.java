package com.ranger.defender.auth.impl;

import com.ranger.defender.auth.AuthenticationToken;

/**
 * @Author ranger
 * @Date 2020/1/19 11:10
 **/
public class SimpleAuthenticationToken implements AuthenticationToken {

    private String username;
    private String passsword;
    private boolean rememberMe;


    public SimpleAuthenticationToken(String username) {
        this.username = username;
    }

    public SimpleAuthenticationToken(String username, String passsword) {
        this.username = username;
        this.passsword = passsword;
    }

    public SimpleAuthenticationToken(String username, String passsword, boolean rememberMe) {
        this.username = username;
        this.passsword = passsword;
        this.rememberMe = rememberMe;
    }

    @Override
    public String username() {
        return this.username;
    }

    @Override
    public String password(){
        return this.passsword;
    }

    @Override
    public boolean isRemeberMe(){
        return this.rememberMe;
    }


}
