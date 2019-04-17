package com.cn.platform.managecenter.service.system.impl;


import com.cn.platform.managecenter.dao.def.system.PermsMapper;
import com.cn.platform.managecenter.dao.def.system.RoleMapper;
import com.cn.platform.managecenter.dao.def.system.UserMapper;
import com.cn.platform.managecenter.dao.def.system.UserRoleMapper;
import com.cn.platform.managecenter.entity.system.Perms;
import com.cn.platform.managecenter.entity.system.Role;
import com.cn.platform.managecenter.entity.system.User;
import com.cn.platform.managecenter.service.system.UserService;
import com.cn.platform.managecenter.utils.TableResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhj on 2018/8/8.
 */
@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermsMapper permsMapper;
    @Resource
    private UserRoleMapper userRoleMapper;


    @Override
    public User getUserByParms(Map<String,Object> map) {

        User u= userMapper.getUserByParms(map);
        if(null==u){
            return null;
        }
        List<Role> roleList=roleMapper.getRolesByUserId(u.getUserId());
        List<Perms> permsList=permsMapper.getPermsByUserId(u.getUserId());
        u.setRoleList(roleList);
        u.setPermsList(permsList);
        return u ;
    }

    @Override
    public TableResult queryUserListPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {

        map.put("pageStart",(pageNo-1)*pageSize);
        map.put("pageEnd",pageSize);
        List<User> userList=userMapper.queryUserListPage(map);
        Long  total= userMapper.queryUserCount(map);
        TableResult<User> tableResult = new TableResult(pageNo,total,userList);
        return tableResult;
    }

    @Override
    @Transactional(value = "defTransactionManager",rollbackFor = Exception.class)
    public void saveUser(User user, String[] roleIds)   {
        Integer userId=  userMapper.saveUser(user);

          List<Map<String,Object>> list=  new ArrayList<>();
          for(String r:roleIds){
              Map<String,Object> map = new HashMap<>();
              map.put("userId",user.getUserId());
              map.put("roleId",r);
              list.add(map);
          }


        userRoleMapper.bathAddUserRole(list);
    }

    @Override
    public void updateUserInfo(User user, String[] roleIds) {
        userMapper.updateUser(user);
        Map<String,Object> map = new HashMap<>();
        map.put("userId",user.getUserId());
        System.out.println(user.getUserId()+"11111111");
        userRoleMapper.deleteUserRoleByParms(map);
        List<Map<String,Object>> list=  new ArrayList<>();
        for(String r:roleIds){
            Map<String,Object> inMap = new HashMap<>();
            inMap.put("userId",user.getUserId());
            inMap.put("roleId",r);
            list.add(inMap);
        }
        userRoleMapper.bathAddUserRole(list);



    }

    @Override
    public void updateUserStatus(User u) {

        userMapper.updateUser(u);

    }

    @Override
    public User getUserAndRoles(Map<String,Object> map) {

        User u= userMapper.getUserByParms(map);
        if(null==u){
            return u;
        }
        List<Role> roleList=roleMapper.getRolesByUserId(u.getUserId());
        u.setRoleList(roleList);
        return u;
    }

    @Override
    public void updateLastLoginTime(Integer userId) {
        userMapper.updateLastLoginTime( userId);
    }

    @Override
    public void updateUserPwd(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<User> getUserByUserNameAndPhone(Map<String, Object> map) {
        return userMapper.selectByUserNameAndPhone(map);
    }

    @Override
    public void updateUserGroup(Map<String, Object> inMap) {
        userMapper.updateUserGroup(inMap);
    }

}
