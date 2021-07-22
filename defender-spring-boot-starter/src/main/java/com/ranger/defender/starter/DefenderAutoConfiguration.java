package com.ranger.defender.starter;

import com.ranger.defender.Defender;
import com.ranger.defender.DefenderManager;
import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.config.DefenderConfig;
import com.ranger.defender.encrypter.Encryptor;
import com.ranger.defender.encrypter.MD5Encryptor;
import com.ranger.defender.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto configure component
 */
@Configuration
@ConditionalOnProperty(value = "defender.enable", havingValue = "true")
@EnableConfigurationProperties(DefenderProperties.class)
public class DefenderAutoConfiguration {

    @Autowired
    private DefenderProperties defenderProperties;

    @Autowired(required = false)
    private Authentication authentication;

    @Autowired(required = false)
    private Authorization authorization;

    @Bean
    public SpringContextUtil contextUtil() {
        return new SpringContextUtil();
    }

    @Bean
    public Encryptor encrypter() {
        return new MD5Encryptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefenderManager defenderManager() {
        DefenderConfig config = new DefenderConfig();
        config.setAuthenType(defenderProperties.getAuthType());
        config.setJwtConfig(defenderProperties.getJwt());
        config.setSessionConfig(config.getSessionConfig());
        DefenderManager defenderManager = new DefenderManager();
        defenderManager.setDefenderConfig(config);
        defenderManager.setAuthentication(authentication);
        defenderManager.setAuthorization(authorization);

        return defenderManager;
    }

    @Bean
    public Defender defender() {
        Defender defender = new Defender();
        defender.setDefenderManager(defenderManager());
        return defender;
    }
}
