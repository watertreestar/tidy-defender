package com.ranger.defender.encrypter;

import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;

/**
 * @Author ranger
 * @Date 2020/1/20 16:16
 **/
public interface Encrypter {

    /**
     * verify the password equals to authentication information
     * @param token
     * @param authenticationInfo
     * @return
     */
    boolean verify(AuthenticationToken token, AuthenticationInfo authenticationInfo);

    default String tokenPassword(AuthenticationToken token){
        return token.password();
    }

    default String authenticationInfoPassword(AuthenticationInfo info){
        return info.password();
    }
}
