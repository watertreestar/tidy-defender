package com.ranger.defender.subject;

import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.exception.UnAuthenticateException;

/**
 * @Author ranger
 * @Date 2020/1/20 11:02
 **/
public abstract class SimpleSubject implements Subject {


    private Authentication authentication;

    private Authorization authorization;

    public SimpleSubject(Authentication authentication, Authorization authorization) {
        this.authentication = authentication;
        this.authorization = authorization;
    }

    public SimpleSubject(){}

    @Override
    public AuthenticationInfo login(AuthenticationToken token) {
        AuthenticationInfo info = this.authentication.doAuthentication(token);
        if(null == info){
            throw UnAuthenticateException.build();
        }
        return null;

    }

    @Override
    public void logOut() {

    }




}
