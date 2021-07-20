package com.ranger.defender.config;

import lombok.Data;

import java.time.Duration;

/**
 * @Author ranger
 * @Date 2020/1/19 9:18
 **/

@Data
public class JWTConfig {
    /**
     * Read the token field from the Http Header
     */
    private String header = "Authorization";

    /**
     * token prefix，@link{https://jwt.io/introduction/}
     */
    private String tokenHead = "Bearer ";

    /**
     * The secret when jwt is signed, be sure to configure, do not leak
     */
    private String secret = "keeper";

    /**
     * The generated token is valid.
     * If the refresh time is not set after expiration, you need to log in again.
     */
    private Duration expires = Duration.ofMinutes(10);

    /**
     * The token can be renew at the latest,
     * in which an expired token can be refreshed to generate a new token.
     * <p>
     * If the time is exceeded, re-authentication is required.
     */
    private Duration renewExpires;
}
