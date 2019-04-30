package com.cn.platform.managecenter.controller.system;


import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.controller.BaseController;
import com.cn.platform.managecenter.entity.AjaxVo;
import com.cn.platform.managecenter.entity.log.OperateLog;
import com.cn.platform.managecenter.entity.system.User;
import com.cn.platform.managecenter.service.system.UserService;
import com.cn.platform.managecenter.utils.Md5Util;
import com.cn.platform.managecenter.utils.PageParam;
import com.cn.platform.managecenter.utils.ShiroUtil;
import com.cn.platform.managecenter.utils.TableResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by dhj on 2018/8/9.
 */
@Controller
@RequestMapping("system/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher logEventPublish;
    /**
     * 用户列表页
     * @return
     */
    @GetMapping("/userPage")
    public String  userPage(){

        return "system/userPage";
    }

    /**
     * 个人信息页面
     * @param model
     * @return
     */
    @GetMapping("/userInfoPage")
    public String userInfoPage(Model model){
        model.addAttribute("userId",getSession().getUserId());

        return  "system/userInfoPage";
    }

    /**
     * 忘记密码页
     * @return
     */
    @GetMapping("/updatePwdPage")
    public String updatePwdPage(){

        return  "system/updatePwdPage";
    }

    /**
     * 用户列表数据
     * @param pageParam
     * @return
     */
    @GetMapping("/userList")
    @ResponseBody
    public TableResult userList(PageParam pageParam){

        TableResult tableResult=null;
      Map<String,Object> inMap= new HashMap<>();
        try{
            if(pageParam.getOtherParams()!=null){
                inMap=pageParam.getOtherParams();
            }
             inMap.put("userType", CommonConstant.MONITOR_DATA_TYPE);
             tableResult =userService.queryUserListPage(pageParam.getPageNum(),pageParam.getPageSize(),inMap);

        }catch (Exception e){

            e.printStackTrace();
        }
     return tableResult;

    }

    /**
     * 添加用户
     * @param user
     * @param roleIds
     * @return
     */
    @PostMapping("/userAdd")
    @ResponseBody
    public AjaxVo userAdd(User user, @RequestParam("roleIds[]") String[] roleIds){
        int groupId = getSession().getGroupId();
        AjaxVo ajaxVo =new AjaxVo();
        if(StringUtils.isBlank(user.getUserName())|| StringUtils.isBlank(user.getNikeName())|| StringUtils.isBlank(user.getPhone())
                || StringUtils.isBlank(user.getEmail())|| roleIds.length<=0){
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
            return  ajaxVo;
        }
        try{
            Map <String,Object> inMap= new HashMap<>();
            inMap.put("userName",user.getUserName());
            inMap.put("phone",user.getPhone());
            inMap.put("userType", CommonConstant.CONTROL_DATA_TYPE);
            List<User> oldUser= userService.getUserByUserNameAndPhone(inMap);
            if(oldUser==null || oldUser.isEmpty()){
                user.setUserType(CommonConstant.CONTROL_DATA_TYPE);
                user.setPassword(ShiroUtil.sha256Hash(Md5Util.MD5Encode(CommonConstant.DEFAULT_PWD)));
                user.setStatus(CommonConstant.STATUS_E);
                user.setGroupId(groupId);
                userService.saveUser(user,roleIds);
            }else{
                ajaxVo.setCode(AjaxVo.ERROR);
                ajaxVo.setMsg(CommonConstant.USERNAME_OR_PHONE_EXISITS);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);

        }
        OperateLog operateLog= new OperateLog();
        operateLog.setOperator(getSession().getUserId());
        operateLog.setBusiness("用户管理");
        operateLog.setType("添加");
        operateLog.setContent("添加用户:"+user.toString()+",用户角色:"+roleIds.toString());
        operateLog.setCreateTime(new Date());
        logEventPublish.publishEvent(operateLog);
        return  ajaxVo;
    }

    /**
     * 获取用户角色信息
     * @param userId
     * @return
     */
    @GetMapping("/getUserRoles")
    @ResponseBody
    public AjaxVo getUserRoles(@RequestParam String userId){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            Map <String,Object> inMap= new HashMap<>();
            inMap.put("userId",userId);
            inMap.put("userType", CommonConstant.MONITOR_DATA_TYPE);
            User user= userService.getUserAndRoles(inMap);
            ajaxVo.setObj(user);
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);

        }
        return  ajaxVo;
    }


    /**
     * 更新用户信息
     * @param user
     * @param roleIds
     * @return
     */
    @PutMapping("/userInfoUpdate")
    @ResponseBody
    public AjaxVo userInfoUpdate(User user, @RequestParam("roleIds[]") String[] roleIds){
        AjaxVo ajaxVo =new AjaxVo();
        if(  user.getUserId()==null  || StringUtils.isBlank(user.getUserName())|| StringUtils.isBlank(user.getNikeName())|| StringUtils.isBlank(user.getPhone())
                || StringUtils.isBlank(user.getEmail())|| roleIds.length<=0){
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
            return  ajaxVo;
        }
        try{
            Map <String,Object> inMap= new HashMap<>();
            inMap.put("userName",user.getUserName());
            inMap.put("phone",user.getPhone());
            inMap.put("userType", CommonConstant.MONITOR_DATA_TYPE);
            User oldUser= userService.getUserByParms(inMap);
            if(oldUser==null ||(oldUser!=null && oldUser.getUserName().equals(user.getUserName()) && oldUser.getPhone().equals(user.getPhone()))){
                userService.updateUserInfo(user,roleIds);
            }else{
                ajaxVo.setCode(AjaxVo.ERROR);
                ajaxVo.setMsg(CommonConstant.USERNAME_OR_PHONE_EXISITS);
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }

    /**
     * 启用停用用户
     * @param userId
     * @param status
     * @return
     */
    @PutMapping("/userStatusUpdate")
    @ResponseBody
    public AjaxVo userStatusUpdate(@RequestParam Integer userId, @RequestParam String status){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            User u= new User();
            u.setUserId(userId);
            u.setStatus(status);
           userService.updateUserStatus(u);
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }

    /**
     * 修改密码
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @GetMapping("/updateUserPwd")
    @ResponseBody
    public AjaxVo updateUserPwd(@RequestParam String oldPwd, @RequestParam String newPwd){
        AjaxVo ajaxVo =new AjaxVo();
        User u=  getSession();
        try{
           if(!ShiroUtil.sha256Hash(oldPwd).equals(u.getPassword())){
               ajaxVo.setCode(AjaxVo.ERROR);
               ajaxVo.setMsg(CommonConstant.OLD_PASSWORD_ERROR);
           }else{
               User user= new User();
               user.setUserId(u.getUserId());
               user.setPassword(ShiroUtil.sha256Hash(newPwd));
               userService.updateUserPwd(user);
               removeUserSession();
           }
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }

    @ResponseBody
    @PostMapping("/updateUserGroup")
    public AjaxVo updateUserChannel(String ids,String channelId){
        AjaxVo ajaxVo =new AjaxVo();
        try{
            Map inMap = new HashMap();
            if(StringUtils.isBlank(ids) && StringUtils.isBlank(channelId+"")){
                ajaxVo.setCode(AjaxVo.ERROR);
                ajaxVo.setMsg(CommonConstant.PARMS_NOT_ENOUGH);
                return  ajaxVo;
            }
            List<String> nameList = new ArrayList<>();
            String channelIds[] = ids.split(",");
            for (int i=0;i<channelIds.length;i++) {
                String id = channelIds[i];
                inMap.put("userId",id);
                inMap.put("channelId",channelId);
                userService.updateUserGroup(inMap);
            }
            ajaxVo.setCode(ajaxVo.SUCCESS);
            ajaxVo.setMsg("成功");
            ajaxVo.setObj(nameList);
        }catch (Exception e){
            e.printStackTrace();
            ajaxVo.setCode(AjaxVo.ERROR);
            ajaxVo.setMsg(CommonConstant.SYS_ERROR);
        }
        return  ajaxVo;
    }





}
