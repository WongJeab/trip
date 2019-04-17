package com.cn.platform.managecenter.controller.system;


import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.controller.BaseController;
import com.cn.platform.managecenter.entity.AjaxVo;
import com.cn.platform.managecenter.entity.system.Role;
import com.cn.platform.managecenter.service.system.RoleService;
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
@RequestMapping("system/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    /**
     * 角色管理页面
     * @return
     */
    @GetMapping("/rolePage")
    public String  rolePage(){

        return "system/rolePage";
    }

    /**
     * 获取所有角色信息
     * @return
     */
    @ResponseBody
    @GetMapping("/getAllRoles")
    public AjaxVo getAllRoles(){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            Map<String,Object> inMap= new HashMap<>();
            inMap.put("roleType", CommonConstant.MONITOR_DATA_TYPE);
         ajaxVo.setObj(roleService.getAllRoles(inMap));
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return ajaxVo;
    }

    /**
     * 角色列表
     * @param pageParam
     * @return
     */
    @GetMapping("/roleList")
    @ResponseBody
    public TableResult roleList(PageParam pageParam){

        TableResult tableResult=null;
        Map<String,Object> inMap= new HashMap<>();
        try{
            if(pageParam.getOtherParams()!=null){
                inMap=pageParam.getOtherParams();
            }
            inMap.put("roleType", CommonConstant.MONITOR_DATA_TYPE);
            tableResult =roleService.queryRoleListPage(pageParam.getPageNum(),pageParam.getPageSize(),inMap);

        }catch (Exception e){

            e.printStackTrace();
        }
        return tableResult;

    }

    /**
     * 角色添加
     * @param role
     * @param permIds
     * @return
     */
    @PostMapping("/roleAdd")
    @ResponseBody
    public AjaxVo roleAdd(Role role, @RequestParam("permIds[]") String[] permIds){
        AjaxVo ajaxVo =new AjaxVo();
        if(StringUtils.isBlank(role.getRoleName())|| StringUtils.isBlank(role.getRole())|| permIds.length<=0){
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
            return  ajaxVo;
        }
        try{
            Map <String,Object> inMap= new HashMap<>();
            inMap.put("role",role.getRole());
            inMap.put("roleType", CommonConstant.MONITOR_DATA_TYPE);
            Role oldRole= roleService.getRoleByRole(inMap);
            if(oldRole==null){
                role.setRoleType(CommonConstant.MONITOR_DATA_TYPE);
                role.setStatus(CommonConstant.STATUS_E);
                roleService.saveRole(role,permIds);
            }else{
                ajaxVo.setCode(AjaxVo.ERROR);
                ajaxVo.setMsg(CommonConstant.ROLE_IS_EXISITS);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);

        }
        return  ajaxVo;
    }

    /**
     * 角色更新
     * @param role
     * @param permIds
     * @return
     */
    @PutMapping("/roleUpdate")
    @ResponseBody
    public AjaxVo roleUpdate(Role role, @RequestParam("permIds[]") String[] permIds){
        AjaxVo ajaxVo =new AjaxVo();
        if(role.getRoleId()==null  || StringUtils.isBlank(role.getRoleName())|| StringUtils.isBlank(role.getRole())|| permIds.length<=0){
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
            return  ajaxVo;
        }
        try{
            Map <String,Object> inMap= new HashMap<>();
            inMap.put("role",role.getRole());
            inMap.put("roleType", CommonConstant.MONITOR_DATA_TYPE);
            Role oldRole= roleService.getRoleByRole(inMap);
            if(oldRole==null ||(oldRole!=null && oldRole.getRole().equals(role.getRole()))){
                role.setRoleType(CommonConstant.MONITOR_DATA_TYPE);
                role.setStatus(CommonConstant.STATUS_E);
                roleService.updateRole(role,permIds);
            }else{
                ajaxVo.setCode(AjaxVo.ERROR);
                ajaxVo.setMsg(CommonConstant.ROLE_IS_EXISITS);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);

        }
        return  ajaxVo;
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @DeleteMapping ("/deleteRole")
    @ResponseBody
    public AjaxVo deleteRole(@RequestParam String roleId){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            Role role= new Role();
            role.setRoleId(Integer.valueOf(roleId));
            role.setRoleType(CommonConstant.MONITOR_DATA_TYPE);
            roleService.deleteRoleByRoleId(role);
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }


    /**
     * 获取角色权限信息
     * @param roleId
     * @return
     */
    @ResponseBody
    @GetMapping("/getRolePerms")
    public AjaxVo getRolePermsTreeList(@RequestParam String roleId){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            Map<String,Object> inMap= new HashMap<>();
            inMap.put("permType", CommonConstant.MONITOR_DATA_TYPE);
            inMap.put("roleType", CommonConstant.MONITOR_DATA_TYPE);
            inMap.put("roleId", roleId);
            ajaxVo.setObj(roleService.getRolePerms(inMap));
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return ajaxVo;
    }



}
