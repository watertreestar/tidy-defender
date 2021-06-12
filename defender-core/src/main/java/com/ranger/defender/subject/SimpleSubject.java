package com.ranger.defender.subject;

import com.ranger.defender.auth.*;
import com.ranger.defender.encrypter.Encrypter;
import com.ranger.defender.exception.PasswordNotCorrectException;
import com.ranger.defender.exception.UnAuthenticateException;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author ranger
 * @Date 2020/1/20 11:02
 **/
public abstract class SimpleSubject implements Subject {

    private Authentication authentication;

    private Authorization authorization;

    public SimpleSubject(Authentication authentication, Authorization authorization) {
        this.authentication = authentication;
        this.authorization = authorization;
    }

    public SimpleSubject(){}

    /**
     * 登录操作
     * 先获取认证信息，然后和传过来的token信息进行对比，如果不相等则抛出异常
     * @param token
     * @return
     */
    @Override
    public AuthenticationInfo login(AuthenticationToken token) {
        AuthenticationInfo info = this.authentication.doAuthentication(token);
        if(null == info){
            throw UnAuthenticateException.build();
        }
        Encrypter encrypter = authentication.encrypter();
        if(null == encrypter || !encrypter.verify(token,info)){
            throw PasswordNotCorrectException.build();
        }
        return info;

    }

    @Override
    public boolean hasPermissions(List<String> roles, List<String> permissions) {
        AuthorizationInfo authorizationInfo = this.authorize();
        if(authorizationInfo == null){
            return false;
        }

        // 先通过角色判断
        if(!CollectionUtils.isEmpty(roles) && !CollectionUtils.isEmpty(authorizationInfo.getRoles())){
            for(String s : roles){
                return authorizationInfo.getRoles().contains(s);
            }
        }

        if(!CollectionUtils.isEmpty(permissions) && !CollectionUtils.isEmpty(authorizationInfo.getPermissions())){
            for(String s : permissions){
                return authorizationInfo.getPermissions().contains(s);
            }
        }

        return false;
    }

    protected AuthorizationInfo authorize(){
        AuthenticationInfo authenticationInfo = this.getAuthenticationInfo();
        return authorization.doAuthorization(authenticationInfo);
    }

}
