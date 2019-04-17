package com.cn.platform.managecenter.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Role {
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 角色标识
     */
    private String role;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 角色数据类型 1 控制中心  2 监控中心
     */
    private  String roleType;
    /**
     * 角色权限
     */
    private List<Perms> permsList;


}