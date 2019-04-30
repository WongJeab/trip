package com.cn.platform.managecenter.dao.def.wx;

import com.cn.platform.managecenter.entity.wx.WxLoginVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/04/29 11:08
 */
@Mapper
public interface WxLoginVoMapper {
    void addWxLogin(WxLoginVo wxLoginVo);

    void updateWxLogin(WxLoginVo wxLoginVo);

    List<WxLoginVo> qryWxLoginListPara(Map<String, Object> inMap);

    void deleteWxLogin(long id);

    List<WxLoginVo> qryWxLoginPage(Map<String, Object> inMap);

    Long qryWxLoginCount(Map<String, Object> inMap);


}
