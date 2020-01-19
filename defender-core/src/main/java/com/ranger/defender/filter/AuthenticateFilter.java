package com.ranger.defender.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author ranger
 * @Date 2020/1/19 9:16
 * 请求认证过滤器
 **/
@Slf4j
public class AuthenticateFilter extends OncePerRequestFilter {

    private List<String> excludesPath;
    private List<String> includesPath;


    private PathMatcher pathMatcher = new AntPathMatcher();


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



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

    }
}
