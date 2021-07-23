package com.ranger.defender.token.generator;

import java.util.Map;

/**
 * @Author ranger
 * @Date 2020/6/7 22:14
 * To create or get token info from jwt
 **/
public interface JwtTokenGenerator {
    /**
     * Create a token that sets the username to subject
     *
     * @param username unique identity of the user currently logged in
     * @param claims   user additional information, please do not store password type information
     * @return return a new JWT token
     */
    String create(String username, Map<String, Object> claims);

    /**
     * Parse the username based on the incoming JWT token
     *
     * @param token jwt token
     * @return username
     */
    String getUsername(String token);

    /**
     * get the create time in seconds for token
     *
     * @param token
     * @return
     */
    long getCreateTime(String token);

    /**
     * get the timeout in seconds for token
     *
     * @param token
     * @return
     */
    long getExpireTime(String token);

    /**
     * get the latest timeout in seconds for a renewal
     *
     * @param token
     * @return
     */
    long getRenewExpireTime(String token);

    /**
     * Verify that the incoming token has expired
     *
     * @param token jwt token
     */
    boolean isExpired(String token);

    /**
     * Verify that the incoming token can be refreshed if the claim to refresh does not expire
     *
     * @param token jwt token
     */
    boolean canRenew(String token);

    /**
     * @return returns token of the current request context, obtained in the header
     */
    String getAuthToken();


    /**
     * Refreshing the current user's JWT token in order to  write a new token to the header of the response.
     * The client should replace this token with the latest accessible JWT token
     *
     * @param username unique identity of the user currently logged in
     * @param claims   user additional information, please do not store password type information
     */
    String refresh(String username, Map<String, Object> claims);
}
