package com.ranger.defender.starter;

import com.ranger.defender.config.JWTConfig;
import com.ranger.defender.config.SessionConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Defender basic configuration
 */
@Data
@ConfigurationProperties("defender")
public class DefenderProperties {
    private JWTConfig jwt = new JWTConfig();
    private SessionConfig session = new SessionConfig();
}
