package com.hzk.backend.services;

import com.hzk.backend.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.services
 * @Project: IT_Project
 * @name: AdminService
 * @Date: 2026/3/19 8:47
 * @Filename: AdminService
 */
@Mapper
public interface AdminService {
    /**
     *
     * @param username
     * @param password
     * @return
     */
    Admin adminLogin(String username, String password);
    int adminRegister(Admin admin);
    int adminUpdate(Admin admin);
    int deleteAdminById(Integer id);
    // List<Admin> searchAdminByName(String name);
    List<Admin> searchAdmin(
            String name,
            String btime,
            String etime,
            Integer state,
            Integer start,
            Integer end);
}
