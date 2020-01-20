package com.ranger.defender.subject;

import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;

/**
 * @Author ranger
 * @Date 2020/1/19 9:43
 * 认证主体
 **/
public interface Subject {
    /**
     *
     * @return
     */
    AuthenticationInfo getAuthenticationInfo();

    /**
     * login,return information represents specific token
     * @param token
     * @return
     */
    AuthenticationInfo login(AuthenticationToken token);

    void logOut();

    void refreshAuthentication();

    boolean isLogin();

    boolean renew();

    /**
     * TODO complete
     * @return
     */
    boolean hasPermissions();
}
