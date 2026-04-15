package com.hzk.backend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.pojo
 * @Project: IT_Project
 * @name: SysUser
 * @Date: 2026/3/19 8:39
 * @Filename: SysUser
 */
public class SysUser {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String remark;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Integer status; // 状态：1正常 0停用

    public SysUser() {}

    public SysUser(String username, Long id, String password, String role, String remark, Date createTime, Integer status) {
        this.username = username;
        this.id = id;
        this.password = password;
        this.role = role;
        this.remark = remark;
        this.createTime = createTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysUser{ " +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                "}";
    }
}
