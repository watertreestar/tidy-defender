package com.ranger.defender.config;

import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.enums.AuthenType;
import lombok.Data;
import lombok.Setter;

/**
 * @Author ranger
 * @Date 2020/1/20 11:10
 **/
@Data
public class DefenderConfig {
    private AuthenType authenType = AuthenType.SESSION;

    private Authentication authentication;

    private Authorization authorization;

    private

    private JwtConfig jwtConfig;

    private SessionConfig sessionConfig;

    private String loginSuccessUrl;

    private String unLoginUrl;
}
