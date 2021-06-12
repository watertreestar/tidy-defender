package com.ranger.defender.auth;

import com.ranger.defender.encrypter.Encrypter;
import com.ranger.defender.exception.DefenderException;

/**
 * @Author ranger
 * @Date 2020/1/19 9:45
 * 执行认证的接口定义
 **/
public interface Authentication {

    /**
     * current encoder for password
     * @return
     */
    Encrypter encrypter();


    /**
     * load authentication information
     * @param token
     * @return
     * @throws DefenderException
     */
    AuthenticationInfo doAuthentication(AuthenticationToken token) throws DefenderException;
}
