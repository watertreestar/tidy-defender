package com.ranger.defender.encrypter;

import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;

/**
 * @Author ranger
 * @Date 2020/1/20 16:44
 **/
public class BCryptEncrypter implements Encrypter {
    @Override
    public boolean verify(AuthenticationToken token, AuthenticationInfo authenticationInfo) {
        return false;
    }
}
