package com.hzk.backend.mapper;

import com.hzk.backend.pojo.BizDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.mapper
 * @Project: IT_Project
 * @name: BizDeviceMapper
 * @Date: 2026/4/17 16:28
 * @Filename: BizDeviceMapper
 */

@Mapper
public interface BizDeviceMapper {
    // 直接用注解写SQL，自动映射下划线字段到驼峰属性
    @Select("SELECT id, device_code as deviceCode, device_name as deviceName, " +
            "device_model as deviceModel, endurance, camera_params as cameraParams, " +
            "owner, status, remark FROM biz_device")
    List<BizDevice> selectAll();
}
