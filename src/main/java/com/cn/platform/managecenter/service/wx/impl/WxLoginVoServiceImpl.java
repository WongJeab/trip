package com.cn.platform.managecenter.service.wx.impl;

import com.cn.platform.managecenter.dao.def.wx.WxLoginVoMapper;
import com.cn.platform.managecenter.entity.wx.WxLoginVo;
import com.cn.platform.managecenter.service.wx.WxLoginVoService;
import com.cn.platform.managecenter.utils.TableResult;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/04/29 16:52
 */
@Service
public class WxLoginVoServiceImpl implements WxLoginVoService {
    @Autowired
    WxLoginVoMapper wxLoginVoDao;

    @Override
    public int addWxLogin(WxLoginVo wxLoginVo) {
        int retCode = -1;
        try {
            wxLoginVoDao.addWxLogin(wxLoginVo);
            retCode = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retCode;
    }

    @Override
    public int updateWxLogin(WxLoginVo wxLoginVo) {
        int retCode = -1;
        try {
            wxLoginVoDao.updateWxLogin(wxLoginVo);
            retCode = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retCode;
    }

    @Override
    public List<WxLoginVo> qryWxLoginListPara(Map<String, Object> inMap) {
        return wxLoginVoDao.qryWxLoginListPara(inMap);
    }

    @Override
    public int deleteWxLogin(long id) {
        int retCode = -1;
        try {
            wxLoginVoDao.deleteWxLogin(id);
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
        List<WxLoginVo> list= wxLoginVoDao.qryWxLoginPage(inMap);
        Long  total= wxLoginVoDao.qryWxLoginCount(inMap);
        TableResult tableResult = new TableResult(pageNum,total,list);
        return tableResult;
    }

    @Override
    public boolean isLoginToken(Map<String,String> headerMap) {
        boolean bool = false;
        Map<String,Object> inMap = new HashMap<>();
        if(StringUtils.isNotBlank(headerMap.get("loginToken"))){
            inMap.put("loginToken",headerMap.get("loginToken"));
            List<WxLoginVo>  loginVoList = wxLoginVoDao.qryWxLoginListPara(inMap);
            if(!loginVoList.isEmpty() && loginVoList.size()>0){
                bool = true;
            }
        }
       return bool;
    }
}
