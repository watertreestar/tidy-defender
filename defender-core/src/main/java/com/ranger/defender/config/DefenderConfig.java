package com.ranger.defender.config;

import com.ranger.defender.encrypter.Encryptor;
import com.ranger.defender.enums.AuthenType;
import lombok.Data;

/**
 * @Author ranger
 * @Date 2020/1/20 11:10
 **/
@Data
public class DefenderConfig {
    /**
     * 认证类型，支持JWT和Session
     */
    private AuthenType authenType = AuthenType.SESSION;

    /**
     * 加密器
     */
    private Encryptor encryptor;

    /**
     * JWT认证的配置，secret,expire and so on
     */
    private JWTConfig jwtConfig;

    /**
     * Session认证的配置
     */
    private SessionConfig sessionConfig;

    /**
     * Session认证方式登录成功后跳转的url地址
     */
    private String loginSuccessUrl;

    /**
     * 未认证用户访问的跳转url
     */
    private String unLoginUrl;
}
