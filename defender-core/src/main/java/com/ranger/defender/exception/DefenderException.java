package com.ranger.defender.exception;

/**
 * @Author ranger
 * @Date 2020/1/19 9:49
 **/
public class DefenderException  extends RuntimeException {

    public DefenderException() {
        super();
    }

    public DefenderException(String message) {
        super(message);
    }

    public DefenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefenderException(Throwable cause) {
        super(cause);
    }

    protected DefenderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
