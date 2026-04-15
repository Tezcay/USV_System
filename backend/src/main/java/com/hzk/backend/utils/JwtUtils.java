package com.hzk.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.utils
 * @Project: IT_Project
 * @name: JwtUtils
 * @Date: 2026/4/15 16:18
 * @Filename: JwtUtils
 */
public class JwtUtils {
    // 生成安全的密钥(实际不能硬编码)
    private static final Key SIGN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Token 的有效时间( 12 小时)
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000L;

    // 生成 JWT Token
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims) // 设置负荷(比如里面放管理员的 id 和 名字)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .signWith(SIGN_KEY) // 签名
                .compact();
    }

    // 解析 JWT Token
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGN_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
