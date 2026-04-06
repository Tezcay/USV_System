package com.hzk.backend.pojo;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.pojo
 * @Project: IT_Project
 * @name: Admin
 * @Date: 2026/3/19 8:39
 * @Filename: Admin
 */
public class Admin {
    private Integer adminId;
    private String adminName;
    private String adminPassword;
    private Integer adminState;
    private String createTime;

    public Admin() {}

    public Admin(Integer adminState, String adminPassword, String createTime, String adminName, Integer adminId) {
        this.adminState = adminState;
        this.adminPassword = adminPassword;
        this.createTime = createTime;
        this.adminName = adminName;
        this.adminId = adminId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Integer getAdminState() {
        return adminState;
    }

    public void setAdminState(Integer adminState) {
        this.adminState = adminState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Admin{ " +
                "adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", adminState=" + adminState +
                ", createTime='" + createTime + '\'' +
                "}";
    }
}
