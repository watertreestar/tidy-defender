package com.ranger.defender.subject;

import com.ranger.defender.util.WebContext;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

/**
 * @Author ranger
 * @Date 2020/1/20 14:05
 **/
public class SessionSubject extends SimpleSubject {

    public HttpSession getSession(){
        return WebContext.getCurrentSession(false);
    }
}
