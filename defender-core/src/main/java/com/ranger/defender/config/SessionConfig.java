package com.ranger.defender.config;

import lombok.Data;

import java.time.Duration;

/**
 * @Author ranger
 * @Date 2020/1/19 9:19
 **/
@Data
public class SessionConfig {
    private String   cookieName = "defender";
    private String   secret     = "defender-343of2";
    private Duration renewExpires;
    private String   domain;
    private String   path       = "/";
    private boolean  secure;
    private boolean  isHttpOnly;
}
