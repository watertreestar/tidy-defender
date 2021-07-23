package com.ranger.defender.aspect;

import com.ranger.defender.Defender;
import com.ranger.defender.DefenderManager;
import com.ranger.defender.annotation.HasPermission;
import com.ranger.defender.annotation.HasRole;
import com.ranger.defender.exception.UnAuthorizedException;
import com.ranger.defender.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.security.Permission;
import java.util.Arrays;

/**
 * @Author ranger
 * @Date 2020/6/10 17:36
 **/

@Aspect
public class AuthorizationAspect {

    @Pointcut("@annotation(com.ranger.defender.annotation.HasPermission) || @annotation(com.ranger.defender.annotation.HasRole)")
    public void authorization(){

    }

    @Before("authorization()")
    public void beforePermission(JoinPoint joinPoint){
        Subject subject = Defender.getCurrentSubject();

        Method method = ((MethodSignature) joinPoint).getMethod();
        HasRole role = method.getAnnotation(HasRole.class);
        HasPermission permission = method.getAnnotation(HasPermission.class);

        String [] requireRoles = role.value();
        String [] requirePermissions = permission.value();

        boolean hasPermission = subject.hasPermissions(Arrays.asList(requireRoles),Arrays.asList(requirePermissions));
        if(!hasPermission){
            throw UnAuthorizedException.build("permission denied");
        }
    }
}
