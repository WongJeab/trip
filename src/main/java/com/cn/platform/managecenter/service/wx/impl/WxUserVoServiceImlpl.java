package com.cn.platform.managecenter.service.wx.impl;

import com.cn.platform.managecenter.dao.def.wx.WxUserVoMapper;
import com.cn.platform.managecenter.entity.wx.WxUserVo;
import com.cn.platform.managecenter.service.wx.WxUserVoService;
import com.cn.platform.managecenter.utils.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * User: wangyingxian
 * Date: 2019/04/29 15:03
 */
@Service
public class WxUserVoServiceImlpl implements WxUserVoService {
    @Autowired
    WxUserVoMapper wxUserVoDao;

    @Override
    public long addWxUser(WxUserVo wxUserVo) {
        long retCode = -1;
        try {
            wxUserVoDao.addWxUser(wxUserVo);
            return wxUserVo.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retCode;
    }

    @Override
    public int updateWxUser(WxUserVo wxUserVo) {
        int retCode = -1;
        try {
            wxUserVoDao.updateWxUser(wxUserVo);
            retCode = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retCode;
    }

    @Override
    public List<WxUserVo> qryWxUserListPara(Map<String, Object> inMap) {
        return wxUserVoDao.qryWxUserListPara(inMap);
    }

    @Override
    public int deleteWxUser(long wxUserId) {
        int retCode = -1;
        try {
            wxUserVoDao.deleteWxUser(wxUserId);
            retCode = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retCode;
    }

    @Override
    public TableResult qryWxUserListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap) {
        inMap.put("pageStart",(pageNum-1)*pageSize);
        inMap.put("pageEnd",pageSize);
        List<WxUserVo> list= wxUserVoDao.qryWxUserPage(inMap);
        Long  total= wxUserVoDao.qryWxUserCount(inMap);
        TableResult tableResult = new TableResult(pageNum,total,list);
        return tableResult;
    }
}
