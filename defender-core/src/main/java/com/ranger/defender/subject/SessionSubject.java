package com.ranger.defender.subject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ranger.defender.DefenderManager;
import com.ranger.defender.auth.Authentication;
import com.ranger.defender.auth.AuthenticationInfo;
import com.ranger.defender.auth.AuthenticationToken;
import com.ranger.defender.auth.Authorization;
import com.ranger.defender.config.SessionConfig;
import com.ranger.defender.exception.UnAuthenticateException;
import com.ranger.defender.util.DateUtil;
import com.ranger.defender.util.WebContext;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;

import static com.ranger.defender.constant.DefenderConstant.SESSION_KEY;

/**
 * @Author ranger
 * @Date 2020/1/20 14:05
 **/
public class SessionSubject extends SimpleAbstractSubject {

    public SessionSubject(Authentication authentication, Authorization authorization, DefenderManager defenderManager) {
        super(authentication,authorization,defenderManager);
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

        // Login successfully, store authentication info
        getSession().setAttribute(SESSION_KEY,authenticationInfo);
        SessionConfig sessionConfig = sessionConfig();
        // 不需要记住我
        if(!token.isRememberMe() || sessionConfig.getRenewExpires() == null) {
            return authenticationInfo;
        }

        String rememberMeToken = generateToken(authenticationInfo.username());
        Cookie cookie = new Cookie(sessionConfig.getCookieName(),rememberMeToken);
        cookie.setPath(sessionConfig.getPath());
        cookie.setSecure(sessionConfig.isSecure());
        cookie.setHttpOnly(sessionConfig.isHttpOnly());
        // cookie的过期时间
        cookie.setMaxAge((int) sessionConfig.getRenewExpires().toMillis() / 1000);
        if (!StringUtils.isEmpty(sessionConfig.getDomain())) {
            cookie.setDomain(sessionConfig.getDomain());
        }

        HttpServletResponse response = WebContext.getCurrentResponse();
        response.addCookie(cookie);

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
        return (AuthenticationInfo) getSession().getAttribute(SESSION_KEY);
    }

    @Override
    public void logOut() {
        HttpSession session = getSession();
        if(null == session){
            return;
        }
        session.removeAttribute(SESSION_KEY);
        HttpServletResponse response = WebContext.getCurrentResponse();
        // 获取记住我的cookie信息
        Cookie cookie = getRenewCookie();
        if (null == cookie) {
            return;
        }
        String token = cookie.getValue();
        cookie.setValue("");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);

        String username = getUsername(token);
        if (StringUtils.isEmpty(username)) {
            return;
        }
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

    /**
     * 获取cookie
     * @return
     */
    private Cookie getRenewCookie() {
        HttpServletRequest request = WebContext.getCurrentRequest();
        Cookie[]           cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(sessionConfig().getCookieName())) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * 生成一个token,保存了用户信息，用来自动登录
     * @param username
     * @return
     */
    private String generateToken(String username) {
        SessionConfig config = sessionConfig();
        JWTCreator.Builder builder = JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(DateUtil.plus(config.getRenewExpires().toMillis()));

        return builder.sign(Algorithm.HMAC256(config.getSecret()));
    }

    /**
     * 从cookie取出的token中获取username
     * @param token
     * @return
     */
    private String getUsername(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        SessionConfig config = sessionConfig();
        try {
            JWTVerifier verifier = JWT.require(
                    Algorithm.HMAC256(config.getSecret()))
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
