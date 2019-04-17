package com.cn.platform.managecenter.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * User: wangyingxian
 * Date: 2019/04/17 09:26
 */
@Setter
@Getter
@ToString
public class Group {
    /**
     * 平台Id
     */
    private Integer id;
    /**
     * 平台上级PId
     */
    private Integer pId;
    /**
     * 上级名称
     */
    private  String upName;
    /**
     * 平台名称
     */
    private String name;
    /**
     * 创建人id
     */
    private int createUId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 上级名称
     */
    private  String status;
    /**
     * 状态
     */
    private String remark;
}
