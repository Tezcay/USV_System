package com.hzk.backend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Author: 102300428_何振坤
 *
 * @Package: com.hzk.backend.pojo
 * @Project: IT_Project
 * @name: SysLog
 * @Date: 2026/4/15 20:26
 * @Filename: SysLog
 */

/*@Data
@Builder // 核心: 建造者模式 想传哪个参数就传哪个，不用管构造方法怎么写
@NoArgsConstructor
@AllArgsConstructor // @Builder 底层需要全参构造，所以要配上*/
public class SysLog {
    private Integer id;
    private String operator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operateTime;

    private String content;
    private String ipAddress;

    public SysLog() {}

    // AOP 需要的 4 参数构造（不包含 id）
    public SysLog(String operator, Date operateTime, String content, String ipAddress) {
        this.operator = operator;
        this.operateTime = operateTime;
        this.content = content;
        this.ipAddress = ipAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
