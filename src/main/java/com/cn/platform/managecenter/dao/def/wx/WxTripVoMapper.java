package com.cn.platform.managecenter.dao.def.wx;

import com.cn.platform.managecenter.entity.wx.WxTripVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/04/29 15:00
 */
@Mapper
public interface WxTripVoMapper {
    void addWxTrip(WxTripVo wxTripVo);

    void updateWxTrip(WxTripVo wxTripVo);

    List<WxTripVo> qryWxTripListPara(Map<String, Object> inMap);

    void deleteWxTrip(long id);

    List<WxTripVo> qryWxTripPage(Map<String, Object> inMap);

    Long qryWxTripCount(Map<String, Object> inMap);

    void updateWxTripStatus(long id);
}
