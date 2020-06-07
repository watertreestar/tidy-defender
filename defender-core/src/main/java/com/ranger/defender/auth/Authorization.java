package com.ranger.defender.auth;

import com.ranger.defender.exception.DefenderException;

/**
 * @Author ranger
 * @Date 2020/1/19 9:45
 * 获取授权信息的接口定义
 **/
public interface Authorization {

    /**
     * load user authorization information contains user roles and permissions
     * @param info    the authenticated user token identifier, mainly username
     * @return
     * @throws DefenderException
     */
    AuthorizationInfo doAuthorization(AuthenticationInfo info) throws DefenderException;
}
