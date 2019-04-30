package com.cn.platform.managecenter.service.wx;

import com.cn.platform.managecenter.entity.wx.WxTripVo;
import com.cn.platform.managecenter.utils.TableResult;

import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/04/29 15:03
 */
public interface WxTripVoService {
    public int addWxTrip(WxTripVo wxTripVo);


    public int updateWxTrip(WxTripVo wxTripVo);


    public List<WxTripVo> qryWxTripListPara(Map<String, Object> inMap);


    public int deleteWxTrip(long id);


    TableResult qryWxTripListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap);
}
