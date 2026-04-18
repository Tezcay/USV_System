package com.hzk.backend.services;

import com.hzk.backend.pojo.SysLog;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.services
 * @Project: IT_Project
 * @name: SysLogService
 * @Date: 2026/4/16 9:59
 * @Filename: SysLogService
 */
public interface SysLogService {
    void insertLog(SysLog log);

    // 查出所有的操作日志，按时间倒序（最新的在最上面）
    List<SysLog> selectAllLogs();
}
