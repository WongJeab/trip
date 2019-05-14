package com.cn.platform.managecenter.service.wx.impl;

import com.cn.platform.managecenter.constant.TripConstant;
import com.cn.platform.managecenter.dao.def.wx.WxTripVoMapper;
import com.cn.platform.managecenter.entity.wx.WxTripVo;
import com.cn.platform.managecenter.service.wx.WxTripVoService;
import com.cn.platform.managecenter.utils.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/04/29 16:50
 */
@Service
public class WxTripVoServiceImpl implements WxTripVoService {
    @Autowired
    WxTripVoMapper wxTripVoDao;

    @Override
    public int addWxTrip(WxTripVo wxTripVo) {
        int retCode = -1;
        try {
            wxTripVo.setStatus(TripConstant.STATUS_E);
            wxTripVoDao.addWxTrip(wxTripVo);
            retCode = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retCode;
    }


    @Override
    public int updateWxTrip(WxTripVo wxTripVo) {
        int retCode = -1;
        try {
            wxTripVoDao.updateWxTrip(wxTripVo);
            retCode = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retCode;
    }


    @Override
    public List<WxTripVo> qryWxTripListPara(Map<String, Object> inMap) {
        return wxTripVoDao.qryWxTripListPara(inMap);
    }


    @Override
    public int deleteWxTrip(long id) {
        int retCode = -1;
        try {
            wxTripVoDao.deleteWxTrip(id);
            retCode = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retCode;
    }


    @Override
    public TableResult qryWxTripListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap) {
        inMap.put("pageStart",(pageNum-1)*pageSize);
        inMap.put("pageEnd",pageSize);
        List<WxTripVo> list= wxTripVoDao.qryWxTripPage(inMap);
        Long  total= wxTripVoDao.qryWxTripCount(inMap);
        TableResult tableResult = new TableResult(pageNum,total,list);
        return tableResult;
    }
}
