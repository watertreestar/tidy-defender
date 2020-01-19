package com.ranger.defender.auth.impl;

import com.ranger.defender.auth.AuthorizationInfo;

import java.util.Set;

/**
 * @Author ranger
 * @Date 2020/1/19 11:17
 * A default implementation of authorization
 **/
public class SimpleAuthorizationInfo implements AuthorizationInfo {
    @Override
    public Set<String> getRoles() {
        return null;
    }

    @Override
    public Set<String> getPermissions() {
        return null;
    }
}
