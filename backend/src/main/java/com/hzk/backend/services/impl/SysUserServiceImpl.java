package com.hzk.backend.services.impl;

import com.hzk.backend.mapper.SysUserMapper;
import com.hzk.backend.pojo.SysUser;
import com.hzk.backend.services.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.services.impl
 * @Project: IT_Project
 * @name: SysUserServiceImpl
 * @Date: 2026/3/19 8:49
 * @Filename: SysUserServiceImpl
 */
@Service // 标识serviceImpl实现类也加入了Spring容器中，已实例化
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser login(String username, String password) {
        // 使唤Mapper去查数据库，并把查到的实体类返回
        return sysUserMapper.login(username, password);
    }

    @Override
    public int register(SysUser sysUser) {
        // 调用 Mapper 去执行插入语句
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int update(SysUser sysUser) {
        // 调用 Mapper 去执行修改语句
        return sysUserMapper.update(sysUser);
    }

    @Override
    public int deleteById(Long id) {
        // 调用 Mapper 去执行删除语句
        return sysUserMapper.deleteById(id);
    }

    /*@Override
    public List<SysUser> searchAdminByName(String name){
        // 调用 Mapper 去执行搜索语句
        return adminMapper.searchAdminByName(name);
    }*/

    @Override
    public List<SysUser> search(String name, String btime, String etime, String role, Integer status, Integer start, Integer end) {
        // 调用 Mapper 去执行搜索语句
        return sysUserMapper.search(name, btime, etime, role, status, start, end);
    }
}
