package com.cn.platform.managecenter.dao.def.wx;

import com.cn.platform.managecenter.entity.wx.WxUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/04/29 14:59
 */
@Mapper
public interface WxUserVoMapper {
    int addWxUser(WxUserVo wxUserVo);

    void updateWxUser(WxUserVo wxUserVo);

    List<WxUserVo> qryWxUserListPara(Map<String,Object> inMap);

    void deleteWxUser(long id);

    List<WxUserVo> qryWxUserPage(Map<String, Object> inMap);

    Long qryWxUserCount(Map<String, Object> inMap);
}
