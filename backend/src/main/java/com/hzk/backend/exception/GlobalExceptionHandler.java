package com.hzk.backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.exception
 * @Project: IT_Project
 * @name: GlobalExceptionHandler
 * @Date: 2026/4/16 8:48
 * @Filename: GlobalExceptionHandler
 */

@RestControllerAdvice // 接收所有的 Controller 抛出的异常
public class GlobalExceptionHandler {

    // 捕获主动抛出的 RuntimeException（比如“权限不足”）
    @ExceptionHandler(RuntimeException.class)
    public Map<String,Object> handleRuntimeException(RuntimeException e){
        Map<String,Object> result = new HashMap<>();
        result.put("code",500); // 500 失败
        result.put("message",e.getMessage());
        result.put("data",null);
        return result;
    }

    // 捕获所有不可预知的系统大错（比如数据库突然断了、空指针等）
    @ExceptionHandler(Exception.class)
    public Map<String,Object> handleException(Exception e){
        // 在后台控制台打印报错日志，留给后端看
        e.printStackTrace();

        Map<String,Object> result = new HashMap<>();
        result.put("code",500);
        result.put("message", "服务器开小差了，请稍后再试~");
        result.put("data",null);
        return result;
    }
}
