package com.cn.platform.managecenter.entity.wx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 登录表（trip_login_info）
 * User: wangyingxian
 * Date: 2019/04/25 12:36
 */
@Getter
@Setter
@ToString
public class WxLoginVo {
    /*id*/
    private long id;

    /*登录人员id*/
    private long loginId;

    /*登录时间*/
    private Date firstLoginTime;

    /*登录时间*/
    private Date lastLoginTime;

    /*过期时间*/
    private long expireTime;//过期时间为空表示长期有效

    /*UUID*/
    private String uuid;

    /*系统时间*/
    private long sysTime;

    /*登录Token*/
    private String loginToken;

    /*ipa Token*/
    private String apiToken;
}
