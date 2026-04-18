package com.hzk.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**") // 拦截所有路径请求
                .excludePathPatterns("/user/login", "/user/register", "/device/**"); // 白名单
    }

    // 全局跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有接口跨域
                .allowedOriginPatterns("*") // 允许所有前端来源 (Vue 的 localhost:8081 等)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许所有请求方法
                .allowedHeaders("*") // 允许携带所有请求头 (包含我们的 Token)
                .allowCredentials(true) // 允许携带 Cookie / 认证信息
                .maxAge(3600); // 把探路兵的存活时间设为 1 小时，不用频繁探路
    }
}
