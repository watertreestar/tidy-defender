package com.ranger.defender.token.generator;

import java.util.Map;

/**
 * A Simple JwtToken implementation
 */
public class SimpleJwtToken implements JwtTokenGenerator {
    @Override
    public String create(String username, Map<String, Object> claims) {
        return null;
    }

    @Override
    public String getUsername(String token) {
        return null;
    }

    @Override
    public long getCreateTime(String token) {
        return 0;
    }

    @Override
    public long getExpireTime(String token) {
        return 0;
    }

    @Override
    public long getRenewExpireTime(String token) {
        return 0;
    }

    @Override
    public boolean isExpired(String token) {
        return false;
    }

    @Override
    public boolean canRenew(String token) {
        return false;
    }

    @Override
    public String getAuthToken() {
        return null;
    }

    @Override
    public String refresh(String username, Map<String, Object> claims) {
        return null;
    }
}
