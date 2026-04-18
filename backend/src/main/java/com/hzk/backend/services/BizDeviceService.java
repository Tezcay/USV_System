package com.hzk.backend.services;

import com.hzk.backend.pojo.BizDevice;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.services
 * @Project: IT_Project
 * @name: BizDeviceService
 * @Date: 2026/4/17 16:30
 * @Filename: BizDeviceService
 */
public interface BizDeviceService {
    List<BizDevice> getAllDevices();
}
