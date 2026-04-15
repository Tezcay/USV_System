package com.hzk.backend.mapper;

import com.hzk.backend.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.mapper
 * @Project: IT_Project
 * @name: SysUserMapper
 * @Date: 2026/3/19 9:20
 * @Filename: SysUserMapper
 */

@Mapper
public interface SysUserMapper {
    // 查询(登录)
    SysUser login(@Param("username") String username, @Param("password") String password);
    // 注册功能
    int insert(SysUser sysUser);
    // 修改功能(传整个 SysUser 对象过来，因为修改的字段可能有很多个)
    int update(SysUser sysUser);
    // 删除功能
    int deleteById(@Param("id") Long id);
    // 简单搜索功能
    // List<SysUser> searchAdminByName(@Param("name") String name);
    // 分页条件搜索功能
    List<SysUser> search(
            @Param("name") String name, // 模糊
            @Param("btime") String btime, // 区间
            @Param("etime") String etime, // 区间
            @Param("role") String role,
            @Param("status") Integer status, // 状态
            @Param("start") Integer start, // 分页
            @Param("end") Integer end // 分页
            );
}
