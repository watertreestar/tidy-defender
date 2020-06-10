package com.ranger.defender.subject;

import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;

import java.util.List;

/**
 * @Author ranger
 * @Date 2020/1/19 9:43
 * 认证主体
 * 可以从认证主体中获取认证信息，授权信息
 * 还可以执行一些操作，比如logout,renew,....
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
     *
     * @return
     */
    boolean hasPermissions(List<String> roles, List<String> permissions);
}
