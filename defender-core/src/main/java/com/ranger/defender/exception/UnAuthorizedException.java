package com.ranger.defender.exception;

import static com.ranger.defender.constant.DefenderConstant.ERROR_MESSAGE_EXPIRED;
import static com.ranger.defender.constant.DefenderConstant.ERROR_MESSAGE_NOT_PERMISSION;

/**
 * @Author ranger
 * @Date 2020/1/20 16:09
 **/
public class UnAuthorizedException extends DefenderException {
    public UnAuthorizedException(String message){
        super(message);
    }

    public static UnAuthorizedException build(){
        return new UnAuthorizedException(ERROR_MESSAGE_NOT_PERMISSION);
    }

    public static UnAuthorizedException build(String message){
        return new UnAuthorizedException(message);
    }
}
