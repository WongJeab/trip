package com.cn.platform.managecenter.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Perms {
    /**
     * 权限Id
     */
    private Integer permId;
    /**
     * 权限标识
     */
    private String perm;
    /**
     * 权限名称
     */
    private String permName;
    /**
     * 权限URI
     */
    private String uri;
    /**
     * 权限类型 1 菜单 2 资源链接
     */
    private String type;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 上级ID
     */
    private String upId;
    /**
     * 上级名称
     */
    private  String upName;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 权限数据类型  1 控制中心 2 监控中心
     */
    private String permType;
    /**
     * ztree勾选映射
     */
    private boolean checked;

}