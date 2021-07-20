package com.ranger.defender.starter;

import com.ranger.defender.config.JWTConfig;
import com.ranger.defender.config.SessionConfig;
import com.ranger.defender.enums.AuthenType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Defender basic configuration
 */
@Data
@ConfigurationProperties("defender")
public class DefenderProperties {
    /**
     * Jwt authentication default
     */
    private AuthenType authType = AuthenType.JWT;
    private JWTConfig jwt = new JWTConfig();
    private SessionConfig session = new SessionConfig();
}
