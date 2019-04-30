package com.cn.platform.managecenter.entity.wx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 行程表（trip_trip_info）
 * User: wangyingxian
 * Date: 2019/04/25 12:49
 */
@Getter
@Setter
@ToString
public class WxTripVo {
    private long id;
    private long  uId;
    private String startPlace;    //途径地点
    private String endPlace;//到达地点
    private int  num;
    private int  type;//乘车类型("1"人找车,"2"车找人
    private String goStartDate;//(出发开始日期)
    private String goEndDate;//(出发结束日期)
    private Date createTime;//(创建时间)       Date
    private Date updateTime;//(更新时间)       Date
    private String status;
    private String remark;
}
