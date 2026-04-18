package com.hzk.backend.services.impl;

import com.hzk.backend.mapper.SysLogMapper;
import com.hzk.backend.pojo.SysLog;
import com.hzk.backend.services.SysLogService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.services.impl
 * @Project: IT_Project
 * @name: SysLogServiceImpl
 * @Date: 2026/4/16 10:01
 * @Filename: SysLogServiceImpl
 */

@Service
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;

    /**
     * 使用 @Async 注解，将该方法交给名为 "logAsyncThreadPool" 的线程池异步执行
     */
    @Override
    @Async("logAsyncThreadPool")
    public void insertLog(SysLog log) {
        // 打印当前执行任务的线程名称，方便在控制台观察
        System.out.println("【异步日志入库】正在执行的线程是: " + Thread.currentThread().getName());
        sysLogMapper.insertLog(log);
    }

    @Override
    public List<SysLog> selectAllLogs() {
        return sysLogMapper.selectAllLogs();
    }
}
