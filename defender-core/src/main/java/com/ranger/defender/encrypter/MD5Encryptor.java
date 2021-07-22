package com.ranger.defender.encrypter;

import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;
import com.ranger.defender.util.EncodeUtil;
import org.springframework.util.StringUtils;

/**
 * @Author ranger
 * @Date 2020/1/20 16:33
 **/
public class MD5Encryptor implements Encryptor {

    @Override
    public boolean verify(AuthenticationToken token, AuthenticationInfo authenticationInfo) {
        String tokenPassword = this.tokenPassword(token);
        String authenticationPassword = this.authenticationInfoPassword(authenticationInfo);

        if(StringUtils.isEmpty(tokenPassword) || StringUtils.isEmpty(authenticationPassword)){
            return false;
        }
        return EncodeUtil.md5(tokenPassword).equals(authenticationPassword);
    }
}
