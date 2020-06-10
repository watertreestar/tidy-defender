package com.ranger.defender.auth;

/**
 * @Author ranger
 * @Date 2020/1/19 9:48
 * the identity of user to authentication
 **/
public interface AuthenticationToken {

    /**
     * return the unique identity
     * @return
     */
    String username();

    default  String password(){
        return null;
    }

    default boolean isRememberMe(){
        return false;
    }

}
