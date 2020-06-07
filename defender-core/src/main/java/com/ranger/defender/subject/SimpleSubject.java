package com.ranger.defender.subject;

import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.encrypter.Encrypter;
import com.ranger.defender.exception.PasswordNotCorrectException;
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

    /**
     * 登录操作
     * 先获取认证信息，然后和传过来的token信息进行对比，如果不相等则抛出异常
     * @param token
     * @return
     */
    @Override
    public AuthenticationInfo login(AuthenticationToken token) {
        AuthenticationInfo info = this.authentication.doAuthentication(token);
        if(null == info){
            throw UnAuthenticateException.build();
        }
        Encrypter encrypter = authentication.encrypter();
        if(null == encrypter || !encrypter.verify(token,info)){
            throw PasswordNotCorrectException.build();
        }
        return info;

    }




}
