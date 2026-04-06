package com.hzk.backend.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.dto
 * @Project: IT_Project
 * @name: JsonDto
 * @Date: 2026/3/19 16:18
 * @Filename: JsonDto
 */
public class JsonDto {
    // 状态码：1表示成功，0表示失败
    private Integer id;
    // 提示信息：返回给前端的文字，比如“登录成功”
    private String message;
    //成功以后调整的页面
    private String location;
    // 数据袋：map储存的需要传输的数据（初始化为一个 HashMap 防止空指针报错）
    private Map<String, Object> datas = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }
}
