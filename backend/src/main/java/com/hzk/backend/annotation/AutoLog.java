package com.hzk.backend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.annotation
 * @Project: IT_Project
 * @name: AutoLog
 * @Date: 2026/4/15 20:24
 * @Filename: AutoLog
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLog {
    String value() default ""; // 用于记录你具体做了什么操作
}
