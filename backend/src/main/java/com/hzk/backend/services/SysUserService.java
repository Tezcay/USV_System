package com.hzk.backend.services;

import com.hzk.backend.pojo.SysUser;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.services
 * @Project: IT_Project
 * @name: SysUserService
 * @Date: 2026/3/19 8:47
 * @Filename: SysUserService
 */
public interface SysUserService {
    SysUser login(String username, String password);
    int register(SysUser sysUser);
    int update(SysUser sysUser);
    int deleteById(Long id);
    // List<SysUser> searchAdminByName(String name);
    List<SysUser> search(
            String name,
            String btime,
            String etime,
            String role,
            Integer status,
            Integer start,
            Integer end);
}
