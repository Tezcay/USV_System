package com.hzk.backend.services.impl;

import com.hzk.backend.mapper.AdminMapper;
import com.hzk.backend.pojo.Admin;
import com.hzk.backend.services.AdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.services.impl
 * @Project: IT_Project
 * @name: AdminServiceImpl
 * @Date: 2026/3/19 8:49
 * @Filename: AdminServiceImpl
 */
@Service // 标识serviceImpl实现类也加入了Spring容器中，已实例化
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin adminLogin(String username, String password) {
        // 使唤Mapper去查数据库，并把查到的实体类返回
        Admin admin = adminMapper.findAdminByNameAndPassword(username, password);
        System.out.println(admin);
        return admin;
    }

    @Override
    public int adminRegister(Admin admin) {
        // 调用 Mapper 去执行插入语句
        return adminMapper.insertAdmin(admin);
    }

    @Override
    public int adminUpdate(Admin admin) {
        // 调用 Mapper 去执行修改语句
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int deleteAdminById(Integer id) {
        // 调用 Mapper 去执行删除语句
        return adminMapper.deleteAdminById(id);
    }

    /*@Override
    public List<Admin> searchAdminByName(String name){
        // 调用 Mapper 去执行搜索语句
        return adminMapper.searchAdminByName(name);
    }*/

    @Override
    public List<Admin> searchAdmin(String name, String btime, String etime, Integer state, Integer start, Integer end) {
        // 调用 Mapper 去执行搜索语句
        return adminMapper.searchAdmin(name, btime, etime, state, start, end);
    }
}
