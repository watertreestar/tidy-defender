package com.ranger.defender.auth;

import java.util.Map;

/**
 * @Author ranger
 * @Date 2020/1/19 9:44
 * 认证信息
 **/
public interface AuthenticationInfo {
    String username();

    String password();

    /**
     * Payload information when logging in, optional
     *
     * @return payload
     */
    default Object payload() {
        return null;
    }

    /**
     * Context information to be stored when logging in, optional
     *
     * @return claims
     */
    default Map<String, Object> claims() {
        return null;
    }
}
