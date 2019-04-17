package com.cn.platform.managecenter.controller.system;

import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.controller.BaseController;
import com.cn.platform.managecenter.entity.AjaxVo;
import com.cn.platform.managecenter.entity.system.Group;
import com.cn.platform.managecenter.service.system.GroupService;
import com.cn.platform.managecenter.utils.PageParam;
import com.cn.platform.managecenter.utils.TableResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/03/25 13:59
 */
@Controller
@RequestMapping("system/group")
public class GroupController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @GetMapping("/groupPage")
    public String  userPage(){
        return "system/groupPage";
    }

    @ResponseBody
    @GetMapping("groupList")
    public TableResult groupList(PageParam pageParam){
        TableResult tableResult=null;
        Map<String,Object> inMap= new HashMap<>();
        try{
            if(pageParam.getOtherParams()!=null){
                inMap=pageParam.getOtherParams();
            }
            tableResult =groupService.queryGroupListPage(pageParam.getPageNum(),pageParam.getPageSize(),inMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return tableResult;
    }

    @PostMapping("/groupAdd")
    @ResponseBody
    public AjaxVo groupAdd(Group group){
        AjaxVo ajaxVo =new AjaxVo();
        if(StringUtils.isBlank(group.getName())){
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
            return  ajaxVo;
        }
        try{
            int userId = getSession().getUserId();
            group.setCreateUId(userId);
            group.setStatus(CommonConstant.STATUS_E);
            groupService.addGroup(group);
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }

    @PutMapping("/groupUpdate")
    @ResponseBody
    public AjaxVo groupUpdate(Group group){
        AjaxVo ajaxVo =new AjaxVo();
        if(group.getId()==null){
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
            return  ajaxVo;
        }
        try{
            groupService.updateGroup(group);
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }

    @DeleteMapping("/deleteGroup")
    @ResponseBody
    public AjaxVo deleteChannel(@RequestParam String id){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            groupService.deleteGroup(Integer.valueOf(id));
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }



    @ResponseBody
    @GetMapping("/queryGroupListAll")
    public AjaxVo queryGroupListAll(){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            Map<String,Object> outMap= new HashMap<>();
            outMap.put("groupList",groupService.queryGroupListAll());
            ajaxVo.setObj(outMap);
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return ajaxVo;
    }


    @ResponseBody
    @GetMapping("/getGroup")
    public AjaxVo getGroup(@RequestParam String id){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            if(StringUtils.isNotBlank(id)){
                Map<String,Object> outMap= new HashMap<>();
                outMap.put("groupObj",groupService.queryGroupById(Integer.valueOf(id)));
                outMap.put("groupList",groupService.queryGroupListAll());
                ajaxVo.setObj(outMap);
            }else{
                ajaxVo.setCode(AjaxVo.ERROR);
                ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return ajaxVo;
    }
}
