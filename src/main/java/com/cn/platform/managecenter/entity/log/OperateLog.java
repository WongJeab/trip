package com.cn.platform.managecenter.entity.log;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class OperateLog {

    private Integer id;

    private String business;

    private String type;

    private String content;

    private Integer operator;

    private Date createTime;

    private String remark;

}