package com.hzk.backend.controller;

import com.hzk.backend.dto.JsonDto;
import com.hzk.backend.pojo.SysLog;
import com.hzk.backend.pojo.SysUser;
import com.hzk.backend.services.SysLogService;
import com.hzk.backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.controller
 * @Project: IT_Project
 * @name: SysLogController
 * @Date: 2026/4/16 9:54
 * @Filename: SysLogController
 */

@RestController
@RequestMapping("/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @GetMapping("/list")
    public JsonDto selectAllLog() {
        System.out.println("---收到前端请求: 尝试获取日志---");

        JsonDto jsonDto = new JsonDto();

        List<SysLog> sysLogs = sysLogService.selectAllLogs();

        // 3. 是否查到数据
        if (sysLogs != null) {
            // 成功，打包成功信息
            jsonDto.setId(1);
            jsonDto.setMessage("获取操作日志成功!");
            // 把查到的结果塞进快递箱的 datas 里
            jsonDto.getDatas().put("sysLogs", sysLogs);
        } else {
            // 失败，打包失败信息
            jsonDto.setId(0);
            jsonDto.setMessage("获取操作日志失败!");
        }

        // 4. 返回json
        return jsonDto;
    }
}
