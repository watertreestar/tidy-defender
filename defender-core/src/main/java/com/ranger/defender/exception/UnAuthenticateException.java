package com.ranger.defender.exception;

import static com.ranger.defender.constant.DefenderConstant.ERROR_MESSAGE_NOT_LOGIN;

/**
 * @Author ranger
 * @Date 2020/1/20 14:24
 **/
public class UnAuthenticateException extends DefenderException {
    public UnAuthenticateException(String message){
        super(message);
    }

    public static UnAuthenticateException build(){
        return new UnAuthenticateException(ERROR_MESSAGE_NOT_LOGIN);
    }

    public static UnAuthenticateException build(String message){
        return new UnAuthenticateException(message);
    }
}
