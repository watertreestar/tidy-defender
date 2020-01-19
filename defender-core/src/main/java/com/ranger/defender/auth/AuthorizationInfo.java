package com.ranger.defender.auth;

import java.util.Set;

/**
 * @Author ranger
 * @Date 2020/1/19 9:45
 * 授权信息
 **/
public interface AuthorizationInfo {
    /**
     * @return a list of roles owned by the user, stored as a string
     */
    Set<String> getRoles();

    /**
     * @return a list of permissions owned by the user, stored as a string
     */
    Set<String> getPermissions();
}
