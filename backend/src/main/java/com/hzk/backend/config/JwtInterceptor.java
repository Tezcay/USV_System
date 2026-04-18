package com.hzk.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.hzk.backend.dto.JsonDto;
import com.hzk.backend.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.config
 * @Project: IT_Project
 * @name: JwtInterceptor
 * @Date: 2026/4/15 16:51
 * @Filename: JwtInterceptor
 */

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 放行浏览器的预检请求（解决跨域拦截问题）
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 2. 从请求头中获取通行证（Token）
        String token = request.getHeader("token");

        // 3. 如果没带通行证，或者通行证为空
        if  (token == null ||  token.isEmpty()) {
            returnErrorResponse(response, "未登录，请先登录!");
            return false;
        }

        // 放行浏览器的 OPTIONS 预检请求（探路兵免死金牌）
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 4. 如果有通行证，验真伪和是否过期
        try {
            Claims claims = JwtUtils.parseToken(token); // 让 JwtUtils 去解析

            // RBAC 权限校验
            // 从 Token 中取用户的角色
            String role = claims.get("role",  String.class);
            // 获取用户当前正在请求的网址
            String requestURI = request.getRequestURI();

            // 如果网址包含"delete", 但role不是admin
            if (requestURI.contains("delete") && !"admin".equals(role)) {
                throw new RuntimeException("越权操作：普通用户禁止删除!");
            }

            return true; // 解析成功则放行
        } catch (Exception e) {
            // 解析失败
            returnErrorResponse(response, e.getMessage());
            return false;
        }
    }

    // 辅助方法：给前端返回统一的 JSON 错误提示
    private void returnErrorResponse(HttpServletResponse response, String message) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        JsonDto jsonDto = new JsonDto();
        jsonDto.setId(0); // 失败
        jsonDto.setMessage(message);

        // JsonDto 转换成 Json 字符串写回前端
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(jsonDto));
    }
}
