package com.ranger.defender;

import com.ranger.defender.config.DefenderConfig;
import com.ranger.defender.enums.AuthenType;
import com.ranger.defender.subject.JwtSession;
import com.ranger.defender.subject.SessionSubject;
import com.ranger.defender.subject.Subject;
import com.ranger.defender.util.SpringContextUtil;

/**
 * @Author ranger
 * @Date 2020/1/20 11:11
 **/
public class DefenderManager {
    private DefenderConfig defenderConfig;

    public DefenderConfig getDefenderConfig() {
        return defenderConfig;
    }

    public void setDefenderConfig(DefenderConfig defenderConfig) {
        this.defenderConfig = defenderConfig;
    }

    public static Subject getCurrentSubject(){
        DefenderManager defenderManager = SpringContextUtil.getBean(DefenderManager.class);
        DefenderConfig defenderConfig = defenderManager.getDefenderConfig();
        if(defenderConfig.getAuthenType().equals(AuthenType.SESSION)){
            return new SessionSubject();
        }else if(defenderConfig.getAuthenType().equals(AuthenType.JWT)){
            return new JwtSession();
        }
        return new SessionSubject();
    }
}
