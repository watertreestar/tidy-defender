package com.ranger.defender;

import com.ranger.defender.subject.Subject;

public class Defender {

    private static DefenderManager defenderManager;

    public void setDefenderManager(DefenderManager defenderManager) {
        Defender.defenderManager = defenderManager;
    }

    public DefenderManager getDefenderManager() {
        return defenderManager;
    }

    /**
     * Gets current subject
     * @return
     */
    public static Subject getCurrentSubject() {
        return  defenderManager.getSubject();
    }
}
