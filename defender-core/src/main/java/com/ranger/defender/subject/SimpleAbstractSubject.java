package com.ranger.defender.subject;

import com.ranger.defender.Defender;
import com.ranger.defender.DefenderManager;
import com.ranger.defender.auth.*;
import com.ranger.defender.config.JWTConfig;
import com.ranger.defender.config.SessionConfig;
import com.ranger.defender.encrypter.Encryptor;
import com.ranger.defender.exception.PasswordNotCorrectException;
import com.ranger.defender.exception.UnAuthenticateException;
import com.ranger.defender.token.generator.JwtTokenGenerator;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author ranger
 * @Date 2020/1/20 11:02
 **/
public abstract class SimpleAbstractSubject implements Subject {

    private Authentication authentication;

    private Authorization authorization;

    private DefenderManager defenderManager;

    public SimpleAbstractSubject(Authentication authentication, Authorization authorization, DefenderManager defenderManager) {
        this.authentication = authentication;
        this.authorization = authorization;
        this.defenderManager = defenderManager;
    }

    /**
     * 获取DefenderManager
     * @return
     */
    public DefenderManager getDefenderManager() {
        return defenderManager;
    }

    public void setDefenderManager(DefenderManager defenderManager) {
        this.defenderManager = defenderManager;
    }

    /**
     * SessionConfig
     * @return
     */
    protected SessionConfig sessionConfig() {
        if(defenderManager == null) {
            throw new IllegalStateException("Not config a DefenderManger");
        }
        return defenderManager.getDefenderConfig().getSessionConfig();
    }

    /**
     * JwtConfig
     * @return
     */
    protected JWTConfig jwtConfig() {
        if(defenderManager == null) {
            throw new IllegalStateException("Not config a DefenderManger");
        }
        return defenderManager.getDefenderConfig().getJwtConfig();
    }

    /**
     * 获取JwtToken generator
     * @return
     */
    protected JwtTokenGenerator jwtTokenGenerator() {
        if(defenderManager == null) {
            throw new IllegalStateException("Not config a DefenderManger");
        }
        return defenderManager.getJwtTokenGenerator();
    }

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
        Encryptor encryptor = authentication.encrypter();
        if(null == encryptor || !encryptor.verify(token,info)){
            throw PasswordNotCorrectException.build();
        }
        return info;

    }

    /**
     * 通过需要的角色和权限来判断是否授权
     * @param roles
     * @param permissions
     * @return
     */
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

    /**
     * 刷新授权信息
     */
    @Override
    public void refreshAuthorization() {
        this.authorize();
    }

    /**
     * 返回权限信息
     * @return
     */
    protected AuthorizationInfo authorize(){
        AuthenticationInfo authenticationInfo = this.getAuthenticationInfo();
        return authorization.doAuthorization(authenticationInfo);
    }

}
