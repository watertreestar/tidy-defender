package com.ranger.defender.auth;

import com.ranger.defender.exception.DefenderException;

/**
 * @Author ranger
 * @Date 2020/1/19 9:45
 * 认证
 **/
public interface Authentication {


    /**
     * load authentication information
     * @param token
     * @return
     * @throws DefenderException
     */
    AuthenticationInfo doAuthentication(AuthenticationToken token) throws DefenderException;


}
