package com.ranger.defender;

import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.config.DefenderConfig;
import com.ranger.defender.enums.AuthenType;
import com.ranger.defender.subject.JwtAbstractSubject;
import com.ranger.defender.subject.SessionAbstractSubject;
import com.ranger.defender.subject.Subject;
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

    public Subject getSubject() {
        AuthenType type = defenderConfig.getAuthenType();
        if(type.equals(AuthenType.SESSION)) {
            return new SessionAbstractSubject(this.authentication,this.authorization);
        } else if(type.equals(AuthenType.JWT)) {
            return new JwtAbstractSubject(this.authentication,this.authorization);
        }
        throw new IllegalArgumentException("Unknown auth type [" + type +"]");
    }
}
