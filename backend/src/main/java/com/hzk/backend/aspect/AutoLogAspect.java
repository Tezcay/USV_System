package com.hzk.backend.aspect;

import com.hzk.backend.annotation.AutoLog;
import com.hzk.backend.mapper.SysLogMapper;
import com.hzk.backend.pojo.SysLog;
import com.hzk.backend.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.aspect
 * @Project: IT_Project
 * @name: AutoLogAspect
 * @Date: 2026/4/15 20:27
 * @Filename: AutoLogAspect
 */

@Aspect
@Component
public class AutoLogAspect {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Around("@annotation(autoLog)")
    public Object recordLog(ProceedingJoinPoint pjp, AutoLog autoLog) throws Throwable{
        // 1. 先执行业务方法
        Object result = pjp.proceed();

        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 2. 尝试从 Token 中获取是谁操作的
            String token = request.getHeader("token");
            String operator = "未知用户";

            if (token != null && !token.isEmpty()) {
                // 情况 A：如果是登录后的常规操作，带着 Token，就从 Token 解析
                Claims claims = JwtUtils.parseToken(token);
                operator = claims.get("username", String.class);
            } else {
                // 情况 B：如果没有 Token（比如此时正在登录），尝试从前端传来的参数里抓取 username
                String loginUsername = request.getParameter("username");
                if (loginUsername != null && !loginUsername.isEmpty()) {
                    operator = loginUsername;
                }
            }

            // 3. 获取其他信息并入库
            String content = autoLog.value();
            String ipAddress = request.getRemoteAddr();

            SysLog sysLog = new SysLog(operator, new Date(), content, ipAddress);
            sysLogMapper.insertLog(sysLog);

            /*// 新的写法（使用 Builder）：
            SysLog sysLog = SysLog.builder()
                    .operator(operator)
                    .operateTime(new Date())
                    .content(content)
                    .ipAddress(ipAddress)
                    // 注意：这里故意不写 .id(...)，因为它是数据库自增的
                    .build();*/

            System.out.println("【AOP拦截成功】" + operator + " 执行了: " + content);
        } catch (Exception e) {
            System.err.println("AOP 日志记录异常：" + e.getMessage());
        }

        return result;
    }
}

/*
// 新的写法（使用 Builder）：
SysLog sysLog = SysLog.builder()
        .operator(operator)
        .operateTime(new Date())
        .content(content)
        .ipAddress(ipAddress)
        // 注意：我们这里故意不写 .id(...)，因为它是数据库自增的！
        .build();

sysLogMapper.insertLog(sysLog);*/
