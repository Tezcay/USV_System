package com.hzk.backend.services.impl;

import com.hzk.backend.mapper.SysUserMapper;
import com.hzk.backend.pojo.SysUser;
import com.hzk.backend.services.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * 新增用户 (写操作)
     * @CacheEvict: 发生写操作时，清空名为 "userListCache" 的所有缓存 (allEntries = true)
     * 作用：解决数据库与缓存的数据一致性问题
     */
    @Override
    @CacheEvict(value = "userListCache", allEntries = true)
    public int register(SysUser sysUser) {
        // 调用 Mapper 去执行插入语句
        return sysUserMapper.insert(sysUser);
    }

    /**
     * 更新用户 (写操作)
     * @CacheEvict: 清空缓存，防止别人查到修改前的老数据
     */
    @Override
    @CacheEvict(value = "userListCache", allEntries = true)
    public int update(SysUser sysUser) {
        // 调用 Mapper 去执行修改语句
        return sysUserMapper.update(sysUser);
    }

    /**
     * 删除用户 (写操作)
     * @CacheEvict: 清空缓存，防止别人查到已经被删除的废数据
     */
    @Override
    @CacheEvict(value = "userListCahce", allEntries = true)
    public int deleteById(Long id) {
        // 调用 Mapper 去执行删除语句
        return sysUserMapper.deleteById(id);
    }

    /*@Override
    public List<SysUser> searchAdminByName(String name){
        // 调用 Mapper 去执行搜索语句
        return adminMapper.searchAdminByName(name);
    }*/

    /**
     * 搜索用户列表 (读操作 - 高频)
     * @Cacheable: 第一次查数据库，并把返回的 List 存入缓存。
     * 之后同样的搜索条件进来，直接从缓存拿，不再走 MySQL！
     */
    @Override
    @Cacheable(value = "userListCache")
    public List<SysUser> search(String name, String btime, String etime, String role, Integer status, Integer start, Integer end) {
        // 调用 Mapper 去执行搜索语句
        return sysUserMapper.search(name, btime, etime, role, status, start, end);
    }
}
