package com.cn.platform.managecenter.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SysLog {
    /**
     * 日志主键Id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 请求方法
     */
    private String httpMethod;
    /**
     * 是否ajx
     */
    private String isAjax;
    /**
     * 远程请求地址
     */
    private String remoteAddr;
    /**
     * 请求host
     */
    private String remoteHost;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 请求方法
     */
    private String requestParameter;
    /**
     * 请求URI
     */
    private String requestUri;
    /**
     * 请求结果
     */
    private String result;
    /**
     * 操作人Id
     */
    private Integer userId;
    /**
     * 日志类型
     */
    private String logType;
    /**
     * 请求耗时
     */
    private Integer  useTime;


}