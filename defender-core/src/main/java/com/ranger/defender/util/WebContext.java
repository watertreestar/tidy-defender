package com.ranger.defender.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author ranger
 * @Date 2020/1/20 10:41
 **/
public class WebContext {
    private static final ThreadLocal<HttpServletRequest>  REQUEST_LOCAL  = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletResponse> RESPONSE_LOCAL = new ThreadLocal<>();


    public static void initContext(HttpServletRequest request,HttpServletResponse response){
        REQUEST_LOCAL.set(request);
        RESPONSE_LOCAL.set(response);
    }

    public static void removeContext(){
        REQUEST_LOCAL.remove();
        RESPONSE_LOCAL.remove();
    }

    public static HttpServletRequest getCurrentRequest(){
        return REQUEST_LOCAL.get();
    }

    public static HttpServletResponse getCurrentResponse(){
        return RESPONSE_LOCAL.get();
    }

    public static HttpSession getCurrentSession(boolean create){
        HttpServletRequest request = REQUEST_LOCAL.get();
        return null != request ? request.getSession(create) : null;
    }



}
