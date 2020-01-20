package com.ranger.defender.exception;

import static com.ranger.defender.constant.DefenderConstant.ERROR_MESSAGE_EXPIRED;

/**
 * @Author ranger
 * @Date 2020/1/20 16:08
 **/
public class ExpiredException extends DefenderException {
    public ExpiredException(String message){
        super(message);
    }

    public static ExpiredException build(){
        return new ExpiredException(ERROR_MESSAGE_EXPIRED);
    }

    public static ExpiredException build(String message){
        return new ExpiredException(message);
    }
}
