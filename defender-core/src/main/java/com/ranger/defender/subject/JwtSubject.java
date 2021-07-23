package com.ranger.defender.subject;

import com.ranger.defender.DefenderManager;
import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.auth.impl.SimpleAuthenticationInfo;

/**
 * @Author ranger
 * @Date 2020/1/20 14:05
 **/
public class JwtSubject extends SimpleAbstractSubject {

    public JwtSubject(Authentication authentication, Authorization authorization, DefenderManager defenderManager) {
        super(authentication,authorization,defenderManager);
    }

    /**
     * 获取认证信息
     * @return
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo() {
        return null;
    }

    @Override
    public void logOut() {

    }

    @Override
    public AuthenticationInfo login(AuthenticationToken token) {
        SimpleAuthenticationInfo authenticInfo =  (SimpleAuthenticationInfo)super.login(token);
        String jwtToken = jwtTokenGenerator().create(token.username(), authenticInfo.claims());

        // 存储登录状态，处理注销
        // this.recordLogin(authenticInfo, jwtToken);
        return authenticInfo;
    }

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public boolean renew() {
        return false;
    }

}
