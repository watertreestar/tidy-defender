package com.ranger.defender.constant;

/**
 * @Author ranger
 * @Date 2020/1/20 14:27
 **/
public interface DefenderConstant {

    String SESSION_KEY = "DEFENDER_USER_TOKEN";
    String AUTHENTICATION_KEY = "authentication:%s";

    String ERROR_MESSAGE_NOT_PERMISSION = "You don't have permission to access";
    String ERROR_MESSAGE_NOT_LOGIN = "Please login";
    String ERROR_MESSAGE_EXPIRED = "Your account has expired";
    String ERROR_MESSAGE_WRONG_PASSWORD = "Your password is wrong";

    String LOGOUT_KEY  = "defender:logout:token:%s";

}
