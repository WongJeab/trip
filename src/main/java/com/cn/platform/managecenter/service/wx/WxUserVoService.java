package com.cn.platform.managecenter.service.wx;

import com.cn.platform.managecenter.entity.wx.WxUserVo;
import com.cn.platform.managecenter.utils.TableResult;

import java.util.List;
import java.util.Map;


/**
 * User: wangyingxian
 * Date: 2019/04/29 15:03
 */
public interface WxUserVoService{
    public long addWxUser(WxUserVo wxUserVo);

    public int updateWxUser(WxUserVo wxUserVo);

    public List<WxUserVo> qryWxUserListPara(Map<String,Object> inMap);

    public int deleteWxUser(long wxUserId);

    TableResult qryWxUserListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap);
}
