package com.tanykoo.fxml;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解 javaFx Controller 的初始化页面方法
 * 当FXML加载完成后 根据页面配置的Controller 查找其初始化方法
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Init {

}
