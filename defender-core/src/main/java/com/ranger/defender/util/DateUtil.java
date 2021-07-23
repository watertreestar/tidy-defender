package com.ranger.defender.util;

import java.util.Date;

public class DateUtil {
    public static Date plus(long millis){
        return new Date(System.currentTimeMillis() + millis);
    }
}
