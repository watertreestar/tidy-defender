package com.ranger.defender.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Auto configure component
 */
@Configuration
@EnableConfigurationProperties(DefenderProperties.class)
public class DefenderAutoConfiguration {
}
