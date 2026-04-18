package com.hzk.backend.pojo;

import java.io.Serializable;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.pojo
 * @Project: IT_Project
 * @name: BizDevice
 * @Date: 2026/4/17 16:26
 * @Filename: BizDevice
 */
public class BizDevice implements Serializable {

    // (可选但推荐) 加上序列化版本号
    private static final long serialVersionUID = 1L;

    private Long id;
    private String deviceCode;
    private String deviceName;
    private String deviceModel;
    private Integer endurance;
    private String cameraParams;
    private String owner;
    private String status;
    private String remark;

    public BizDevice() {}

    public BizDevice(Long id, String deviceCode, String deviceName,
                     String deviceModel, Integer endurance, String cameraParams,
                     String owner, String status, String remark) {
        this.id = id;
        this.deviceCode = deviceCode;
        this.deviceName = deviceName;
        this.deviceModel = deviceModel;
        this.endurance = endurance;
        this.cameraParams = cameraParams;
        this.owner = owner;
        this.status = status;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public Integer getEndurance() {
        return endurance;
    }

    public void setEndurance(Integer endurance) {
        this.endurance = endurance;
    }

    public String getCameraParams() {
        return cameraParams;
    }

    public void setCameraParams(String cameraParams) {
        this.cameraParams = cameraParams;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
