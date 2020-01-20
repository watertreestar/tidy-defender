package com.ranger.defender.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author ranger
 * @Date 2020/1/20 14:13
 **/
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T getBean(Class<T> beanType){
        return applicationContext.getBean(beanType);
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
}
