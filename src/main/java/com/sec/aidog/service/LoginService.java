package com.sec.aidog.service;

import java.lang.annotation.*;

/*
 * 安全认证
 * */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginService {

    /**
     * 是否已登录
     * @return
     */
    boolean isLogin() default true;
}
