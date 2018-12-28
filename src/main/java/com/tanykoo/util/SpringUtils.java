package com.tanykoo.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * @Author ThinkPad
 * Created : 2018-06-13 9:55
 * @Since
 */
public class SpringUtils {

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring/application.xml");

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static<T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }

    public static boolean containsBean(String name){
        return applicationContext.containsBean(name);
    }

    public static void registerBeans(HashMap<String,BeanDefinition> beanDefinitionHashMap){
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        for(String beanname : beanDefinitionHashMap.keySet()){
            beanFactory.registerBeanDefinition(beanname,beanDefinitionHashMap.get(beanname));
        }
    }
    public static void removeBean(String beanName){
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        beanFactory.removeBeanDefinition(beanName);
    }
}
