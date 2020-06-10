package com.ranger.defender.exception;

/**
 * @Author ranger
 * @Date 2020/6/10 17:15
 **/
public class AuthenticateException extends DefenderException {
    public AuthenticateException(String message){
        super(message);
    }

    public static AuthenticateException build(String message){
        return new AuthenticateException(message);
    }
}
