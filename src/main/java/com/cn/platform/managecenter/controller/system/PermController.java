package com.cn.platform.managecenter.controller.system;


import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.controller.BaseController;
import com.cn.platform.managecenter.entity.AjaxVo;
import com.cn.platform.managecenter.entity.system.Perms;
import com.cn.platform.managecenter.service.system.PermsService;
import com.cn.platform.managecenter.utils.PageParam;
import com.cn.platform.managecenter.utils.TableResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhj on 2018/8/9.
 */
@Controller
@RequestMapping("system/perm")
public class PermController extends BaseController {
    @Autowired
    private PermsService permsService;

    /**
     * 权限管理页面
     * @return
     */
    @GetMapping("/permsPage")
    public String  userPage(){

        return "system/permsPage";
    }

    /**
     * 权限菜单树
     * @return
     */
    @ResponseBody
    @GetMapping("/getZtreePermList")
    public AjaxVo getZtreePermList(){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            Map<String,Object> inMap= new HashMap<>();
            inMap.put("permType", CommonConstant.MONITOR_DATA_TYPE);
            ajaxVo.setObj(permsService.selectAllPerms(inMap));
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return ajaxVo;
    }

    /**
     * 权限列表数据
     * @param pageParam
     * @return
     */
    @ResponseBody
    @GetMapping("/permList")
    public TableResult permList(PageParam pageParam){

        TableResult tableResult=null;
        Map<String,Object> inMap= new HashMap<>();
        try{
            if(pageParam.getOtherParams()!=null){
                inMap=pageParam.getOtherParams();
            }
            inMap.put("permType", CommonConstant.MONITOR_DATA_TYPE);

            tableResult =permsService.queryPermListPage(pageParam.getPageNum(),pageParam.getPageSize(),inMap);

        }catch (Exception e){

            e.printStackTrace();
        }
        return tableResult;
    }

    /**
     * 权限添加
     * @param perms
     * @return
     */
    @PostMapping("/permAdd")
    @ResponseBody
    public AjaxVo roleAdd(Perms perms){
        AjaxVo ajaxVo =new AjaxVo();
        if(StringUtils.isBlank(perms.getPerm())|| StringUtils.isBlank(perms.getPermName())){
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
            return  ajaxVo;
        }
        try{
            Map <String,Object> inMap= new HashMap<>();
            inMap.put("perm",perms.getPerm());
            inMap.put("permType", CommonConstant.MONITOR_DATA_TYPE);
            Perms oldPerms=permsService.getPermsByPerm(inMap);
            if(oldPerms==null){
                perms.setPermType(CommonConstant.MONITOR_DATA_TYPE);
                perms.setStatus(CommonConstant.STATUS_E);
                permsService.savePerms(perms);
            }else{
                ajaxVo.setCode(AjaxVo.ERROR);
                ajaxVo.setMsg(CommonConstant.PERM_IS_EXISITS);
            }

        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);

        }
        return  ajaxVo;
    }

    /**
     * 权限更新
     * @param perms
     * @return
     */
    @PutMapping("/permUpdate")
    @ResponseBody
    public AjaxVo permUpdate(Perms perms){
        AjaxVo ajaxVo =new AjaxVo();
        if(perms.getPermId()==null||  StringUtils.isBlank(perms.getPerm())|| StringUtils.isBlank(perms.getPermName())){
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
            return  ajaxVo;
        }
        try{
            Map <String,Object> inMap= new HashMap<>();
            inMap.put("perm",perms.getPerm());
            inMap.put("permType", CommonConstant.MONITOR_DATA_TYPE);
            Perms oldPerms=permsService.getPermsByPerm(inMap);
            if(oldPerms==null ||(oldPerms!=null && oldPerms.getPerm().equals(perms.getPerm()))){
                perms.setPermType(CommonConstant.MONITOR_DATA_TYPE);
                perms.setStatus(CommonConstant.STATUS_E);
                permsService.updatePerms(perms);
            }else{
                ajaxVo.setCode(AjaxVo.ERROR);
                ajaxVo.setMsg(CommonConstant.PERM_IS_EXISITS);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);

        }
        return  ajaxVo;
    }

    /**
     * 删除权限
     * @param permId
     * @return
     */
    @DeleteMapping ("/deletePerm")
    @ResponseBody
    public AjaxVo deletePerm(@RequestParam String permId){
        AjaxVo ajaxVo =new AjaxVo();
        try{

           permsService.deletePermByPermId(Integer.valueOf(permId));
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }

    /**
     * 获取权限信息
     * @param permId
     * @return
     */
    @ResponseBody
    @GetMapping("/getPerm")
    public AjaxVo getPerm(@RequestParam String permId){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            Map<String,Object> inMap= new HashMap<>();
            inMap.put("permType", CommonConstant.MONITOR_DATA_TYPE);
            inMap.put("permId", permId);
            Map<String,Object> outMap= new HashMap<>();
            outMap.put("permObj",permsService.getPermsByPerm(inMap));
            outMap.put("treeObj",permsService.selectAllPerms(inMap));
            ajaxVo.setObj(outMap);
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return ajaxVo;
    }




}
