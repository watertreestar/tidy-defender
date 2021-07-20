package com.ranger.defender.starter;

import com.ranger.defender.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto configure component
 */
@Configuration
@EnableConfigurationProperties(DefenderProperties.class)
public class DefenderAutoConfiguration {

    @Autowired
    private DefenderProperties defenderProperties;

    @Bean
    public SpringContextUtil contextUtil() {
        return new SpringContextUtil();
    }




}
