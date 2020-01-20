package com.ranger.defender.exception;

import static com.ranger.defender.constant.DefenderConstant.ERROR_MESSAGE_WRONG_PASSWORD;

/**
 * @Author ranger
 * @Date 2020/1/20 16:12
 **/
public class PasswordNotCorrectException extends DefenderException{
    public PasswordNotCorrectException(String message){
        super(message);
    }

    public static PasswordNotCorrectException build(){
        return new PasswordNotCorrectException(ERROR_MESSAGE_WRONG_PASSWORD);
    }

    public static PasswordNotCorrectException build(String message){
        return new PasswordNotCorrectException(message);
    }
}
