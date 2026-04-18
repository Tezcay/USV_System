package com.hzk.backend.controller;

import com.hzk.backend.annotation.AutoLog;
import com.hzk.backend.dto.JsonDto;
import com.hzk.backend.mapper.BizDeviceMapper;
import com.hzk.backend.pojo.BizDevice;
import com.hzk.backend.pojo.SysLog;
import com.hzk.backend.services.BizDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.controller
 * @Project: IT_Project
 * @name: BizDeviceController
 * @Date: 2026/4/17 16:32
 * @Filename: BizDeviceController
 */

@RestController
@RequestMapping("/device")
public class BizDeviceController {

    @Autowired
    private BizDeviceService bizDeviceService;

    // 🌟自动记录操作日志！
    @AutoLog("查询了无人船设备列表")
    @GetMapping("/list")
    public JsonDto list() {

        JsonDto jsonDto = new JsonDto();

        List<BizDevice> list = bizDeviceService.getAllDevices();

        // 3. 是否查到数据
        if (list != null) {
            // 成功，打包成功信息
            jsonDto.setId(1);
            jsonDto.setMessage("获取设备列表成功!");
            // 把查到的结果塞进快递箱的 datas 里
            jsonDto.getDatas().put("deviceList", list);
        } else {
            // 失败，打包失败信息
            jsonDto.setId(0);
            jsonDto.setMessage("获取操作日志失败!");
        }

        // 4. 返回json
        return jsonDto;
    }
}

