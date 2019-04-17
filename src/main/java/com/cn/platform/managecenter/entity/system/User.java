package com.cn.platform.managecenter.entity.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class User  implements Serializable{
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 别名
     */
    private String nikeName;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
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
     * 最近登录时间
     */
    private Date lastLoginTime;

    /**
     * 用户角色集合
     */
    private List<Role> roleList;
    /**
     * 用户权限集合
     */
    private List<Perms> permsList;
    /**
     * 用户数据类型  1 控制中心  2监控中心
     */
    private  String userType;

    //分组ID
    private  Integer groupId;

    /**
     * 分组名称
     */
    private  String groupName;


}