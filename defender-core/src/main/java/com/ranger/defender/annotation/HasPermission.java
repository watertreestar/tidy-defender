package com.ranger.defender.annotation;

/**
 * @Author ranger
 * @Date 2020/6/10 17:34
 * 是否包含权限
 **/
public @interface HasPermission {
    String[] value();
}
