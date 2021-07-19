package com.ranger.defender;

import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.config.DefenderConfig;
import com.ranger.defender.enums.AuthenType;
import com.ranger.defender.subject.JWTSession;
import com.ranger.defender.subject.SessionSubject;
import com.ranger.defender.subject.Subject;
import com.ranger.defender.util.SpringContextUtil;
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

    public DefenderConfig getDefenderConfig() {
        return defenderConfig;
    }

    private static DefenderManager instance = new DefenderManager();

    public static DefenderManager getInstance() {
        return instance;
    }

    private static DefenderManager me() {
        return getInstance();
    }

    public void setDefenderConfig(DefenderConfig defenderConfig) {
        this.defenderConfig = defenderConfig;
    }

    /**
     * 获取当前认证主体
     * @return
     */
    public static Subject getCurrentSubject() {
        DefenderManager self = me();
        if (self.authentication == null) {
            throw new IllegalStateException("Can not find a Authentication");
        }
        if (self.authorization == null) {
            throw new IllegalStateException("Can not find a Authorization");
        }
        DefenderManager defenderManager = SpringContextUtil.getBean(DefenderManager.class);
        DefenderConfig defenderConfig = defenderManager.getDefenderConfig();
        if (defenderConfig.getAuthenType().equals(AuthenType.SESSION)) {
            return new SessionSubject(self.authentication, self.authorization);
        } else if (defenderConfig.getAuthenType().equals(AuthenType.JWT)) {
            return new JWTSession(self.authentication, self.authorization);
        }
        // Create a SessionSubject default
        return new SessionSubject(self.authentication, self.authorization);
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
}
