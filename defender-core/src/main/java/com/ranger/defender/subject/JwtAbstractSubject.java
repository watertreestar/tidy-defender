package com.ranger.defender.subject;

import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.Authorization;

/**
 * @Author ranger
 * @Date 2020/1/20 14:05
 **/
public class JwtAbstractSubject extends SimpleAbstractSubject {

    public JwtAbstractSubject(Authentication authentication, Authorization authorization) {
        super(authentication,authorization);
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo() {
        return null;
    }

    @Override
    public void logOut() {

    }

    @Override
    public void refreshAuthentication() {

    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public boolean renew() {
        return false;
    }

}
