package com.hzk.backend.mapper;

import com.hzk.backend.pojo.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.mapper
 * @Project: IT_Project
 * @name: SysLogMapper
 * @Date: 2026/4/15 20:26
 * @Filename: SysLogMapper
 */

@Mapper
public interface SysLogMapper {
    int insertLog(SysLog log);

    // 查出所有的操作日志，按时间倒序（最新的在最上面）
    List<SysLog> selectAllLogs();
}
