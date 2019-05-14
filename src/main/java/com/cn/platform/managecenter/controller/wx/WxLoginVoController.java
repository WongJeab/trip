package com.cn.platform.managecenter.controller.wx;

import com.cn.platform.managecenter.entity.AjaxVo;
import com.cn.platform.managecenter.entity.wx.WxLoginVo;
import com.cn.platform.managecenter.entity.wx.WxUserVo;
import com.cn.platform.managecenter.service.wx.WxLoginVoService;
import com.cn.platform.managecenter.service.wx.WxUserVoService;
import com.cn.platform.managecenter.utils.Md5Util;
import com.cn.platform.managecenter.utils.TokenUtil;
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
 * Date: 2019/04/29 15:02
 */
@Controller
@RequestMapping("wx/wxLogin")
public class WxLoginVoController {
    @Autowired
    WxLoginVoService wxLoginVoService;

    @Autowired
    WxUserVoService wxUserVoService;
    //注册-登录
    @RequestMapping("wxRegister")
    @ResponseBody
    public AjaxVo wxRegister(WxUserVo wxUserVo){
        AjaxVo ajaxVo = new AjaxVo();
        Map<String,Object> inMap = new HashMap<>();
        Map<String,Object> retMap = new HashMap<>();
        Map<String,Object> cookieMap = new HashMap<>();
        try {
            //注册判断
            inMap.put("phone",wxUserVo.getPhone().trim());
            List<WxUserVo> wxUserVoList = wxUserVoService.qryWxUserListPara(inMap);
            if(wxUserVoList.isEmpty()){
                wxUserVo.setPwd(Md5Util.MD5Encode(wxUserVo.getPwd()));
                long userId = wxUserVoService.addWxUser(wxUserVo);
                if(userId != -1){
                    long sysTime = TokenUtil.getSysTime();
                    String uuid = TokenUtil.getUUID();
                    String secret = TokenUtil.getApiSecret(uuid);
                    String loginToken = TokenUtil.getLoginToken(userId,sysTime,uuid);
                    String apiToken = TokenUtil.getApiToken(userId,sysTime,uuid,secret);
                    WxLoginVo wxLoginVo = new WxLoginVo();
                    wxLoginVo.setLoginId(userId);
                    wxLoginVo.setUuid(uuid);
                    wxLoginVo.setSysTime(sysTime);
                    wxLoginVo.setLoginToken(loginToken);
                    wxLoginVo.setApiToken(apiToken);
                    wxLoginVoService.addWxLogin(wxLoginVo);

                    cookieMap.put("secret",secret);
                    cookieMap.put("loginToken",loginToken);
                    cookieMap.put("apiToken",apiToken);
                    retMap.put("wxToken",cookieMap);
                    ajaxVo.setObj(retMap);
                    ajaxVo.setMsg("注册成功");
                    ajaxVo.setCode(AjaxVo.ERROR);
                    return ajaxVo;
                }else{
                    ajaxVo.setMsg("用户添加失败");
                    ajaxVo.setCode(AjaxVo.ERROR);
                    return ajaxVo;
                }
            }else{
               ajaxVo.setMsg("注册号码已存在");
               ajaxVo.setCode(AjaxVo.ERROR);
                return ajaxVo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxVo.setMsg(e.getMessage());
            ajaxVo.setCode(AjaxVo.ERROR);
        }
        //登录返回session
        return ajaxVo;
    }

    /*登录 未使用*/
    @RequestMapping("wxLogin")
    @ResponseBody
    public AjaxVo wxLogin(String phone,String pwd){
        AjaxVo ajaxVo = new AjaxVo();
        Map<String,Object> inMap = new HashMap<>();
        Map<String,Object> retMap = new HashMap<>();
        Map<String,Object> cookieMap = new HashMap<>();
        try {
            if(StringUtils.isBlank(phone) || StringUtils.isBlank(pwd)){
                ajaxVo.setMsg("请输入电话和姓名");
                ajaxVo.setCode(AjaxVo.ERROR);
                return ajaxVo;
            }
            //注册判断
            inMap.put("phone",phone.trim());
            inMap.put("pwd",Md5Util.MD5Encode(pwd.trim()));
            List<WxUserVo> wxUserVoList = wxUserVoService.qryWxUserListPara(inMap);
            if(!wxUserVoList.isEmpty() && wxUserVoList.size()>0){
                long  userId= wxUserVoList.get(0).getId();
                long sysTime = TokenUtil.getSysTime();
                String uuid = TokenUtil.getUUID();
                String secret = TokenUtil.getApiSecret(uuid);
                String loginToken = TokenUtil.getLoginToken(userId,sysTime,uuid);
                String apiToken = TokenUtil.getApiToken(userId,sysTime,uuid,secret);
                cookieMap.put("secret",secret);
                cookieMap.put("loginToken",loginToken);
                cookieMap.put("apiToken",apiToken);
                retMap.put("wxToken",cookieMap);
                ajaxVo.setObj(retMap);
                ajaxVo.setMsg("登录成功");
                ajaxVo.setCode(AjaxVo.ERROR);
                return ajaxVo;
            }else{
                ajaxVo.setMsg("查询不到该信息");
                ajaxVo.setCode(AjaxVo.ERROR);
                return ajaxVo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxVo.setMsg(e.getMessage());
            ajaxVo.setCode(AjaxVo.ERROR);
        }
        return ajaxVo;
    }

    /*修改密码 未使用*/
    @RequestMapping("wxGetPassword")
    @ResponseBody
    public AjaxVo wxGetPassword(String phone,String name){
        AjaxVo ajaxVo = new AjaxVo();
        Map<String,Object> inMap = new HashMap<>();
        try {
            if(StringUtils.isBlank(phone) || StringUtils.isBlank(name)){
                ajaxVo.setMsg("请输入电话和姓名");
                ajaxVo.setCode(AjaxVo.ERROR);
                return ajaxVo;
            }
            //注册判断
            inMap.put("phone",phone.trim());
            inMap.put("name",name.trim());
            List<WxUserVo> wxUserVoList = wxUserVoService.qryWxUserListPara(inMap);
            if(!wxUserVoList.isEmpty() && wxUserVoList.size()>0){
                ajaxVo.setMsg("查询成功");
                ajaxVo.setCode(AjaxVo.SUCCESS);
                return ajaxVo;
            }else{
                ajaxVo.setMsg("查询不到该信息");
                ajaxVo.setCode(AjaxVo.ERROR);
                return ajaxVo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxVo.setMsg(e.getMessage());
            ajaxVo.setCode(AjaxVo.ERROR);
        }
        return ajaxVo;
    }

}
