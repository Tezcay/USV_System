package com.hzk.backend.mapper;

import com.hzk.backend.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.mapper
 * @Project: IT_Project
 * @name: AdminMapper
 * @Date: 2026/3/19 9:20
 * @Filename: AdminMapper
 */

@Mapper
public interface AdminMapper {
    // 查询(登录)
    Admin findAdminByNameAndPassword(@Param("name") String name, @Param("password") String password);
    // 注册功能
    int insertAdmin(Admin admin);
    // 修改功能(传整个 Admin 对象过来，因为修改的字段可能有很多个)
    int updateAdmin(Admin admin);
    // 删除功能
    int deleteAdminById(@Param("id") Integer id);
    // 简单搜索功能
    // List<Admin> searchAdminByName(@Param("name") String name);
    // 分页条件搜索功能
    List<Admin> searchAdmin(
            @Param("name") String name, // 模糊
            @Param("btime") String btime, // 区间
            @Param("etime") String etime, // 区间
            @Param("state") Integer state, // 状态
            @Param("start") Integer start, // 分页
            @Param("end") Integer end // 分页
            );
}
