package com.ranger.defender.util;

import com.ranger.defender.auth.AuthenticationToken;
import com.ranger.defender.auth.impl.SimpleAuthenticationToken;

/**
 * @Author ranger
 * @Date 2020/1/20 8:59
 **/
public class TokenBuilder {

    public static AuthorizeTokenBuilder create(){
        return new AuthorizeTokenBuilder();
    }

    static class AuthorizeTokenBuilder {
        String username;
        String password;
        boolean rememberMe;

        public AuthorizeTokenBuilder rememberMe(boolean rememberMe){
            this.rememberMe = rememberMe;
            return this;
        }

        public AuthorizeTokenBuilder username(String username){
            this.username = username;
            return this;
        }

        public AuthorizeTokenBuilder password(String password){
            this.password = password;
            return this;
        }

        public AuthenticationToken build(){
            SimpleAuthenticationToken authenticationToken = new SimpleAuthenticationToken(username,password,rememberMe);
            return authenticationToken;
        }

    }
}
