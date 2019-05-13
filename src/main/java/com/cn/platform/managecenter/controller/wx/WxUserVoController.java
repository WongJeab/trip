package com.cn.platform.managecenter.controller.wx;

import com.cn.platform.managecenter.controller.BaseController;
import com.cn.platform.managecenter.entity.AjaxVo;
import com.cn.platform.managecenter.entity.wx.WxLoginVo;
import com.cn.platform.managecenter.entity.wx.WxUserVo;
import com.cn.platform.managecenter.service.wx.WxLoginVoService;
import com.cn.platform.managecenter.service.wx.WxUserVoService;
import com.cn.platform.managecenter.utils.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("wx/wxUser")
public class WxUserVoController extends BaseController {
    @Autowired
    WxUserVoService wxUserVoService;

    @Autowired
    WxLoginVoService loginVoService;

    @RequestMapping("wxAddUser")
    @ResponseBody
    public AjaxVo wxAddUser(WxUserVo wxUserVo){
        AjaxVo ajaxVo = new AjaxVo();
        try {
            Map<String,String> headerMap = getRequestHeader();
            boolean isLoginToken = loginVoService.isLoginToken(headerMap);
            if(isLoginToken) {
                long retCode = wxUserVoService.addWxUser(wxUserVo);
                if (retCode == -1) {
                    ajaxVo.setMsg("添加成功");
                    ajaxVo.setCode(AjaxVo.SUCCESS);
                } else {
                    ajaxVo.setMsg("添加失败");
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

    @RequestMapping("wxUpdateUser")
    @ResponseBody
    public AjaxVo wxUpdateUser(WxUserVo wxUserVo){
        AjaxVo ajaxVo = new AjaxVo();
        try {
            Map<String,String> headerMap = getRequestHeader();
            boolean isLoginToken = loginVoService.isLoginToken(headerMap);
            if(isLoginToken){
                int retCode = wxUserVoService.updateWxUser(wxUserVo);
                if(retCode==0){
                    ajaxVo.setMsg("APP更新成功");
                    ajaxVo.setCode(AjaxVo.SUCCESS);
                    return ajaxVo;
                }else{
                    ajaxVo.setMsg("APP更新失败");
                    ajaxVo.setCode(AjaxVo.ERROR);
                    return ajaxVo;
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

    @RequestMapping("wxWebUpdatePara")
    @ResponseBody
    public AjaxVo wxWebUpdatePara(String phone,String pwd){
        AjaxVo ajaxVo = new AjaxVo();
        Map<String,Object> inMap = new HashMap<>();
        try {
            if(StringUtils.isBlank(phone)|| StringUtils.isBlank(pwd)){
                ajaxVo.setMsg("参数缺失");
                ajaxVo.setCode(AjaxVo.ERROR);
                return ajaxVo;
            }
            inMap.put("phone",phone);
            //inMap.put("pwd",pwd);
            List<WxUserVo> wxUserVoList = wxUserVoService.qryWxUserListPara(inMap);
            if(!wxUserVoList.isEmpty() && wxUserVoList.size()>0){
                WxUserVo wxUserVo =wxUserVoList.get(0);
                wxUserVo.setPwd(Md5Util.MD5Encode(pwd));
                int retCode = wxUserVoService.updateWxUser(wxUserVo);
                if(retCode==0){
                    ajaxVo.setMsg("密码更新成功");
                    ajaxVo.setCode(AjaxVo.SUCCESS);
                    return ajaxVo;
                }else{
                    ajaxVo.setMsg("密码更新失败");
                    ajaxVo.setCode(AjaxVo.ERROR);
                    return ajaxVo;
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

    @RequestMapping("wxQryMyUser")
    @ResponseBody
    public AjaxVo wxQryMyUser(){
        AjaxVo ajaxVo = new AjaxVo();
        Map<String,Object> inMap = new HashMap<>();
        Map<String,Object> inMap1 = new HashMap<>();
        Map<String,Object> retMap = new HashMap<>();
        try {
            Map<String,String> headerMap = getRequestHeader();
            boolean isLoginToken = loginVoService.isLoginToken(headerMap);
            if(isLoginToken){
                inMap.put("loginToken",headerMap.get("loginToken"));
                List<WxLoginVo>  loginVoList = loginVoService.qryWxLoginListPara(inMap);
                if(!loginVoList.isEmpty()){
                    inMap1.put("id",loginVoList.get(0).getId());
                    List<WxUserVo> wxUserVoList = wxUserVoService.qryWxUserListPara(inMap1);
                    retMap.put("wxUserVo",wxUserVoList.get(0));
                    ajaxVo.setMsg("查询成功");
                    ajaxVo.setCode(AjaxVo.SUCCESS);
                    ajaxVo.setObj(retMap);
                }
            }else{
                ajaxVo.setMsg("请先登录");
                ajaxVo.setCode(AjaxVo.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxVo;
    }

    @RequestMapping("wxQryUserListPara")
    @ResponseBody
    public AjaxVo wxQryUserListPara(String name,String nickName){
        AjaxVo ajaxVo = new AjaxVo();
        Map<String,Object> inMap = new HashMap<>();
        Map<String,Object> retMap = new HashMap<>();
        try {
            Map<String,String> headerMap = getRequestHeader();
            boolean isLoginToken = loginVoService.isLoginToken(headerMap);
            if(isLoginToken){
                inMap.put("name",name);
                inMap.put("nickName",nickName);
                List<WxUserVo> wxUserVoList = wxUserVoService.qryWxUserListPara(inMap);
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

    @RequestMapping("wxDeleteUser")
    @ResponseBody
    public AjaxVo wxDeleteUser(long id){
        AjaxVo ajaxVo = new AjaxVo();
        try {
            Map<String,String> headerMap = getRequestHeader();
            boolean isLoginToken = loginVoService.isLoginToken(headerMap);
            if(isLoginToken){
                int retCode = wxUserVoService.deleteWxUser(id);
                if(retCode==0){
                    ajaxVo.setMsg("APP删除成功");
                    ajaxVo.setCode(AjaxVo.SUCCESS);
                    return ajaxVo;
                }else{
                    ajaxVo.setMsg("APP删除失败");
                    ajaxVo.setCode(AjaxVo.ERROR);
                    return ajaxVo;
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

    /*个人信息登录判断*/
    @RequestMapping("wxQryUser")
    @ResponseBody
    public AjaxVo wxQryUser(){
        AjaxVo ajaxVo = new AjaxVo();
        Map<String,Object> inMap = new HashMap<>();
        try {
            Map<String,String> headerMap = getRequestHeader();
            boolean isLoginToken = loginVoService.isLoginToken(headerMap);
            if(isLoginToken){
                inMap.put("loginToken",headerMap.get("loginToken"));
                List<WxLoginVo> loginVoList =loginVoService.qryWxLoginListPara(inMap);
                if(!loginVoList.isEmpty() && loginVoList.size()>0){
                    WxLoginVo wxLoginVo = loginVoList.get(0);
                    //ajaxVo.setObj(wxLoginVo);
                    ajaxVo.setMsg("APP查询成功");
                    ajaxVo.setCode(AjaxVo.SUCCESS);
                    return ajaxVo;
                }else{
                    ajaxVo.setMsg("APP查询失败");
                    ajaxVo.setCode(AjaxVo.SUCCESS);
                    return ajaxVo;
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
}
