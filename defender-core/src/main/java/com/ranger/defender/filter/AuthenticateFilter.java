package com.ranger.defender.filter;

import com.ranger.defender.Defender;
import com.ranger.defender.DefenderManager;
import com.ranger.defender.exception.AuthenticateException;
import com.ranger.defender.exception.UnAuthenticateException;
import com.ranger.defender.subject.Subject;
import com.ranger.defender.util.WebContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author ranger
 * @Date 2020/1/19 9:16
 * 请求认证过滤器
 **/
@Slf4j
public class AuthenticateFilter extends OncePerRequestFilter {

    private static final Logger logger  = LoggerFactory.getLogger(AuthenticateFilter.class);

    private List<String> excludesPath;
    private List<String> includesPath;

    private PathMatcher pathMatcher = new AntPathMatcher();

    public AuthenticateFilter(){
        this.excludesPath = new ArrayList<>();
        this.includesPath = new ArrayList<>();
    }


    public AuthenticateFilter addPathPatterns(String... urls) {
        return addPathPatterns(Arrays.asList(urls));
    }

    public AuthenticateFilter addPathPatterns(List<String> patterns) {
        this.includesPath.addAll(patterns);
        return this;
    }

    public AuthenticateFilter excludePathPatterns(String... urls) {
        return excludePathPatterns(Arrays.asList(urls));
    }

    public AuthenticateFilter excludePathPatterns(List<String> patterns) {
        this.excludesPath.addAll(patterns);
        return this;
    }

    /**
     * 是否需要进行权限过滤， false - 不进行权限过滤，  true - 需要进行权限过滤
     * @param pathToMatch
     * @param pathMatcher
     * @return
     */
    private boolean matches(String pathToMatch, PathMatcher pathMatcher) {
        PathMatcher pathMatcherToUse = pathMatcher == null ? this.pathMatcher : pathMatcher;
        // 首先判断是否在 excludePath 外
        if (!ObjectUtils.isEmpty(this.excludesPath)) {
            for (String path : this.excludesPath) {
                if (pathMatcherToUse.match(path, pathToMatch)) {
                    return false;
                }
            }
        }
        // 默认所有的路径都需要过滤
        if (ObjectUtils.isEmpty(this.includesPath)) {
            return true;
        }
        // 需要过滤的路径
        for (String pattern : this.includesPath) {
            if (pathMatcherToUse.match(pattern, pathToMatch)) {
                return true;
            }
        }

        return false;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = httpServletRequest.getRequestURI();
        //  setting web context
        WebContext.initContext(httpServletRequest,httpServletResponse);
        // whether to skip the URI
        if (!matches(requestPath, pathMatcher)) {
            this.doFilter(httpServletRequest, httpServletResponse, filterChain);
            WebContext.removeContext();
            return;
        }
        // execute filter
        boolean isAuth = false;       // 是否认证成功
        try{
            isAuth = doAuthentication(httpServletRequest,httpServletResponse);
        }catch (Exception e){
            authenticateException(httpServletRequest,httpServletResponse,e);
            WebContext.removeContext();
            return;
        }

        if(isAuth){
            // authenticate successfully,execute following steps
            doFilter(httpServletRequest,httpServletResponse,filterChain);
        }else{
            // authenticate failed,throw unAuthenticated exception
            unAuthenticated(httpServletRequest,httpServletResponse);
        }
        WebContext.removeContext();
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    private boolean doAuthentication(HttpServletRequest request,HttpServletResponse response){
        // get current subject, determine whether login

        // 在这里判断是否登录，如果没有登录，直接deny
        Subject subject = Defender.getCurrentSubject();
        if(subject.isLogin()){
            return true;
        }
        return false;
    }

    /**
     * 认证失败处理  返回错误码和错误信息
     * @param request
     * @param response
     */
    protected void unAuthenticated(HttpServletRequest request,HttpServletResponse response){
        throw UnAuthenticateException.build("authenticate failed");
    }

    /**
     * 认证过程发生异常处理
     * @param request
     * @param response
     * @param e
     */
    protected void authenticateException(HttpServletRequest request, HttpServletResponse response, Exception e){
        throw AuthenticateException.build(e.getMessage());
    }


}
