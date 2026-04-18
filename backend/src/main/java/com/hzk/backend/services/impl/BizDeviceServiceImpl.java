package com.hzk.backend.services.impl;

import com.hzk.backend.mapper.BizDeviceMapper;
import com.hzk.backend.pojo.BizDevice;
import com.hzk.backend.services.BizDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.services.impl
 * @Project: IT_Project
 * @name: BizDeviceServiceImpl
 * @Date: 2026/4/17 16:30
 * @Filename: BizDeviceServiceImpl
 */

@Service
public class BizDeviceServiceImpl implements BizDeviceService {

    @Autowired
    private BizDeviceMapper bizDeviceMapper;

    /**
     * 获取所有设备列表
     * @Cacheable: 将结果存入缓存。下次查询直接走缓存，不查数据库！
     */
    @Override
    @Cacheable(value = "deviceCache", key = "'allList'")
    public List<BizDevice> getAllDevices() {
        // 这行打印只会在第一次（或者缓存清空时）出现
        System.out.println("【性能监控】缓存未命中，正在执行 MySQL 物理查询获取设备列表...");
        return bizDeviceMapper.selectAll();
    }
}
