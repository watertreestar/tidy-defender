package com.ranger.defender.subject;

import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.exception.UnAuthenticateException;
import com.ranger.defender.util.WebContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.ranger.defender.constant.DefenderConstant.SESSION_KEY;

/**
 * @Author ranger
 * @Date 2020/1/20 14:05
 **/
public class SessionAbstractSubject extends SimpleAbstractSubject {

    public SessionAbstractSubject(Authentication authentication, Authorization authorization) {
        super(authentication,authorization);
    }


    public HttpSession getSession(){
        return WebContext.getCurrentSession(true);
    }


    @Override
    public AuthenticationInfo login(AuthenticationToken token){
        if(getSession() == null ) {
            return null;
        }
        if(token == null) {
            throw UnAuthenticateException.build();
        }

        AuthenticationInfo authenticationInfo = super.login(token);

        // login successfully, store authentication info
        getSession().setAttribute(SESSION_KEY,authenticationInfo);
        // TODO remember me and expire time

        return authenticationInfo;

    }

    /**
     * 获取主体的authenticationInfo, 从session中获取
     * @return
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo() {
        // if offline,return null;
        if(!isLogin()){
            return null;
        }
        AuthenticationInfo authenticationInfo = (AuthenticationInfo) getSession().getAttribute(SESSION_KEY);
        return authenticationInfo;
    }

    @Override
    public void logOut() {
        HttpSession session = getSession();
        if(null == session){
            return;
        }

        session.removeAttribute(SESSION_KEY);
        HttpServletResponse response = WebContext.getCurrentResponse();


    }

    @Override
    public void refreshAuthentication() {

    }

    @Override
    public boolean isLogin() {
        HttpSession session = getSession();
        if(session == null){
            return false;
        }

        Object authenticationInfo = session.getAttribute(SESSION_KEY);
        if(authenticationInfo != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean renew() {
        return false;
    }


}
