package com.ranger.defender;

import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.config.DefenderConfig;
import com.ranger.defender.enums.AuthenType;
import com.ranger.defender.subject.JwtSubject;
import com.ranger.defender.subject.SessionSubject;
import com.ranger.defender.subject.Subject;
import com.ranger.defender.token.generator.JwtTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author ranger
 * @Date 2020/1/20 11:11
 **/
public class DefenderManager {

    private Logger logger = LoggerFactory.getLogger(DefenderManager.class);

    /**
     * 主要的配置
     */
    private DefenderConfig defenderConfig;

    /**
     * 用于认证
     */
    private Authentication authentication;

    /**
     * 用于鉴权
     */
    private Authorization authorization;

    /**
     * 默认使用session的认证方式
     */
    private AuthenType authenType = AuthenType.SESSION;

    private JwtTokenGenerator jwtTokenGenerator;

    public DefenderConfig getDefenderConfig() {
        return defenderConfig;
    }

    private static DefenderManager instance = new DefenderManager();

    public void setDefenderConfig(DefenderConfig defenderConfig) {
        this.defenderConfig = defenderConfig;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public void setJwtTokenGenerator(JwtTokenGenerator generator) {
        this.jwtTokenGenerator = generator;
    }

    public JwtTokenGenerator getJwtTokenGenerator() {
        return jwtTokenGenerator;
    }

    public Subject getSubject() {
        AuthenType type = defenderConfig.getAuthenType();
        if(type.equals(AuthenType.SESSION)) {
            return new SessionSubject(this.authentication,this.authorization,this);
        } else if(type.equals(AuthenType.JWT)) {
            return new JwtSubject(this.authentication,this.authorization,this);
        }
        throw new IllegalArgumentException("Unknown auth type [" + type +"]");
    }
}
