package com.cn.platform.managecenter.controller.wx;

import com.cn.platform.managecenter.constant.TripConstant;
import com.cn.platform.managecenter.controller.BaseController;
import com.cn.platform.managecenter.entity.AjaxVo;
import com.cn.platform.managecenter.entity.wx.WxLoginVo;
import com.cn.platform.managecenter.entity.wx.WxTripVo;
import com.cn.platform.managecenter.service.wx.WxLoginVoService;
import com.cn.platform.managecenter.service.wx.WxTripVoService;
import com.cn.platform.managecenter.utils.PageParam;
import com.cn.platform.managecenter.utils.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/04/29 15:03
 */
@Controller
@RequestMapping("wx/wxTrip")
public class WxTripVoController extends BaseController {
    @Autowired
    WxTripVoService wxTripVoService;
    @Autowired
    WxLoginVoService loginVoService;

    @RequestMapping("wxAddTrip")
    @ResponseBody
    public AjaxVo wxAddTrip(WxTripVo wxTripVo){
        AjaxVo ajaxVo = new AjaxVo();
        try {
            Map<String,String> headerMap = getRequestHeader();
            Map<String,Object> checkMap = loginVoService.isLoginToken(headerMap);
            if(TripConstant.BOOLEAN_TRUE.equals(checkMap.get("loginFlag").toString())) {
                WxLoginVo wxLoginVo = (WxLoginVo) checkMap.get("wxLoginVo");
                wxTripVo.setUId(wxLoginVo.getLoginId());
                long retCode = wxTripVoService.addWxTrip(wxTripVo);
                if (retCode != -1) {
                    ajaxVo.setMsg("wxTrip添加成功");
                    ajaxVo.setCode(AjaxVo.SUCCESS);
                } else {
                    ajaxVo.setMsg("wxTrip添加失败");
                    ajaxVo.setCode(AjaxVo.ERROR);
                }
            }else{
                ajaxVo.setMsg("请先登录");
                ajaxVo.setCode(AjaxVo.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxVo.setMsg(e.getMessage());
            ajaxVo.setCode(AjaxVo.ERROR);
        }
        return ajaxVo;
    }

    @RequestMapping("wxUpdateTrip")
    @ResponseBody
    public AjaxVo wxUpdateTrip(WxTripVo wxTripVo){
        AjaxVo ajaxVo = new AjaxVo();
        Map<String,Object> inMap = new HashMap<>();
        try {
            Map<String,String> headerMap = getRequestHeader();
            Map<String,Object> checkMap = loginVoService.isLoginToken(headerMap);
            if(TripConstant.BOOLEAN_TRUE.equals(checkMap.get("loginFlag").toString())) {
                WxLoginVo wxLoginVo = (WxLoginVo) checkMap.get("wxLoginVo");
                if(wxLoginVo.getLoginId()==wxTripVo.getUId()){
                    long retCode = wxTripVoService.updateWxTrip(wxTripVo);
                    if (retCode == -1) {
                        ajaxVo.setMsg("wxTrip更新成功");
                        ajaxVo.setCode(AjaxVo.SUCCESS);
                    } else {
                        ajaxVo.setMsg("wxTrip更新失败");
                        ajaxVo.setCode(AjaxVo.ERROR);
                    }
                }
            }else{
                ajaxVo.setMsg("请先登录");
                ajaxVo.setCode(AjaxVo.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxVo.setMsg(e.getMessage());
            ajaxVo.setCode(AjaxVo.ERROR);
        }
        return ajaxVo;
    }


    @RequestMapping("wxQryUserListPara")
    @ResponseBody
    public AjaxVo wxQryUserListPara(){
        AjaxVo ajaxVo = new AjaxVo();
        Map<String,Object> inMap = new HashMap<>();
        Map<String,Object> retMap = new HashMap<>();
        try {
            Map<String,String> headerMap = getRequestHeader();
            Map<String,Object> checkMap = loginVoService.isLoginToken(headerMap);
            if(TripConstant.BOOLEAN_TRUE.equals(checkMap.get("loginFlag").toString())) {
                List<WxTripVo> wxUserVoList = wxTripVoService.qryWxTripListPara(inMap);
                retMap.put("wxUserVoList",wxUserVoList);
                ajaxVo.setMsg("查询成功");
                ajaxVo.setCode(AjaxVo.SUCCESS);
                ajaxVo.setObj(retMap);
            }else{
                ajaxVo.setMsg("请先登录");
                ajaxVo.setCode(AjaxVo.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxVo;
    }


    @RequestMapping("wxDeleteTrip")
    @ResponseBody
    public AjaxVo wxDeleteTrip(long id){
        AjaxVo ajaxVo = new AjaxVo();
        try {
            Map<String,String> headerMap = getRequestHeader();
            Map<String,Object> checkMap = loginVoService.isLoginToken(headerMap);
            if(TripConstant.BOOLEAN_TRUE.equals(checkMap.get("loginFlag").toString())) {
                long retCode = wxTripVoService.deleteWxTrip(id);
                if (retCode == -1) {
                    ajaxVo.setMsg("wxTrip删除成功");
                    ajaxVo.setCode(AjaxVo.SUCCESS);
                } else {
                    ajaxVo.setMsg("wxTrip删除失败");
                    ajaxVo.setCode(AjaxVo.ERROR);
                }
            }else{
                ajaxVo.setMsg("请先登录");
                ajaxVo.setCode(AjaxVo.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxVo.setMsg(e.getMessage());
            ajaxVo.setCode(AjaxVo.ERROR);
        }
        return ajaxVo;
    }

    @ResponseBody
    @GetMapping("/qryMyWxTripListPage")
    public AjaxVo qryMyWxTripListPage(PageParam pageParam){
        AjaxVo ajaxVo = new AjaxVo();
        TableResult tableResult=null;
        Map<String,Object> inMap= new HashMap<>();
        Map<String,Object> objMap= new HashMap<>();
        try{
            Map<String,String> headerMap = getRequestHeader();
            Map<String,Object> checkMap = loginVoService.isLoginToken(headerMap);
            if(TripConstant.BOOLEAN_TRUE.equals(checkMap.get("loginFlag").toString())) {
                if(pageParam.getOtherParams()!=null){
                    inMap=pageParam.getOtherParams();
                }
                tableResult =wxTripVoService.qryWxTripListPage(pageParam.getPageNum(),pageParam.getPageSize(),inMap);
                objMap.put("myTripList",tableResult);
                ajaxVo.setObj(objMap);
                ajaxVo.setCode(AjaxVo.SUCCESS);
                ajaxVo.setMsg("个人列表查询成功");
            }else{
                ajaxVo.setMsg("请先登录");
                ajaxVo.setCode(AjaxVo.ERROR);
            }

        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setMsg(e.getMessage());
            ajaxVo.setCode(AjaxVo.ERROR);
        }
        return ajaxVo;
    }

    @ResponseBody
    @GetMapping("/qryWxTripListPage")
    public AjaxVo qryWxTripListPage(PageParam pageParam){
        AjaxVo ajaxVo = new AjaxVo();
        TableResult tableResult=null;
        Map<String,Object> inMap= new HashMap<>();
        Map<String,Object> objMap= new HashMap<>();
        try{
            if(pageParam.getOtherParams()!=null){
                inMap=pageParam.getOtherParams();
            }
            tableResult =wxTripVoService.qryWxTripListPage(pageParam.getPageNum(),pageParam.getPageSize(),inMap);
            objMap.put("myTripList",tableResult);
            ajaxVo.setObj(objMap);
            ajaxVo.setCode(AjaxVo.SUCCESS);
            ajaxVo.setMsg("ALL列表查询成功");
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setMsg(e.getMessage());
            ajaxVo.setCode(AjaxVo.ERROR);
        }
        return ajaxVo;
    }


}
