package com.cn.platform.managecenter.service.wx;

import com.cn.platform.managecenter.entity.wx.WxLoginVo;
import com.cn.platform.managecenter.utils.TableResult;

import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/04/29 15:02
 */
public interface WxLoginVoService {
    public int addWxLogin(WxLoginVo wxLoginVo);


    public int updateWxLogin(WxLoginVo wxLoginVo);


    public List<WxLoginVo> qryWxLoginListPara(Map<String, Object> inMap);


    public int deleteWxLogin(long id);


    TableResult qryWxTripListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap);

    boolean isLoginToken(Map<String,String> headerMap);
}
