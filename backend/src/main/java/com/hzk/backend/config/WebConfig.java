package com.hzk.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.config
 * @Project: IT_Project
 * @name: WebConfig
 * @Date: 2026/4/15 17:03
 * @Filename: WebConfig
 */

// 系统的全局配置(告诉系统安检门在哪)
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**") // 拦截所有路径请求
                .excludePathPatterns("/user/login", "/user/register");
    }
}
