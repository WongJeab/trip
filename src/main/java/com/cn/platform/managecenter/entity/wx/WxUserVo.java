package com.cn.platform.managecenter.entity.wx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户表（trip_user_info）
 * User: wangyingxian
 * Date: 2019/04/25 12:37
 */
@Getter
@Setter
@ToString
public class WxUserVo {
    private long id;
    private String city;//(城市)
    private String province;//(省份)
    private String nickname;//(昵称)
    private String phone;//(姓名)
    private String pwd;//(密码)
    private String name;//(姓名)
    private Date createTime;//(创建时间)
    private Date updateTime;//(更新时间)
    private String remark;//(备注)
}
