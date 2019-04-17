package com.cn.platform.managecenter.service.system.impl;


import com.cn.platform.managecenter.dao.def.system.PermsMapper;
import com.cn.platform.managecenter.dao.def.system.RoleMapper;
import com.cn.platform.managecenter.dao.def.system.RolePermMapper;
import com.cn.platform.managecenter.dao.def.system.UserRoleMapper;
import com.cn.platform.managecenter.entity.system.Perms;
import com.cn.platform.managecenter.entity.system.Role;
import com.cn.platform.managecenter.entity.system.User;
import com.cn.platform.managecenter.service.system.RoleService;
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
 * Created by dhj on 2018/8/13.
 */
@Service
@Qualifier("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermMapper rolePermMapper;
    @Resource
    private PermsMapper permsMapper;
    @Resource
    private UserRoleMapper userRoleMapper;


    @Override
    public List<Role> getAllRoles(Map<String, Object> map) {

        return roleMapper.getAllRoles(map);
    }

    @Override
    public TableResult queryRoleListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap) {

        inMap.put("pageStart",(pageNum-1)*pageSize);
        inMap.put("pageEnd",pageSize);
        List<Role> userList=roleMapper.queryRoleListPage(inMap);
        Long  total= roleMapper.queryRoleCount(inMap);
        TableResult<User> tableResult = new TableResult(pageNum,total,userList);
        return tableResult;
    }

    @Override
    public Role getRoleByRole(Map<String, Object> map) {

        return roleMapper.getRoleByParms(map);
    }

    @Override
    public void saveRole(Role role, String[] permIds) {
          roleMapper.saveRole(role);
        List<Map<String,Object>> list=  new ArrayList<>();
        for(String p:permIds){
            Map<String,Object> map = new HashMap<>();
            map.put("roleId",role.getRoleId());
            map.put("permId",p);
            list.add(map);
        }
        rolePermMapper.bathAddRolePerm(list);

    }

    @Override
    public void updateRole(Role role, String[] permIds) {
        roleMapper.updateRole(role);
        List<Map<String,Object>> list=  new ArrayList<>();
        for(String p:permIds){
            Map<String,Object> map = new HashMap<>();
            map.put("roleId",role.getRoleId());
            map.put("permId",p);
            list.add(map);
        }
        rolePermMapper.deletePermByRoleId(role.getRoleId());
        rolePermMapper.bathAddRolePerm(list);

    }

    @Override
    public void deleteRoleByRoleId(Role role) {
         roleMapper.deleteByRoleId(role);//删除关系待实现
        Map<String,Object> inMap=  new HashMap<>();
        inMap.put("roleId",role.getRoleId());
        userRoleMapper.deleteUserRoleByParms(inMap);
         rolePermMapper.deletePermByRoleId(role.getRoleId());


    }
    @Override
    public Role getRolePerms(Map<String, Object> inMap) {
          Role role= roleMapper.getRoleByParms(inMap);
          if(role==null){
              return null;
          }
          List<Perms> list=permsMapper.selectAllPerms(inMap);
          List<Integer> rolePerm=rolePermMapper.getPermIdByRoleId(inMap);
        if(list==null ||list.size()<1||rolePerm==null ||rolePerm.size()<1){
            role.setPermsList(list);
            return role;
        }
        for(Perms p:list){
            for(Integer rp:rolePerm){
                if(p.getPermId().equals(rp)){//找到匹配数据退出内层循环
                    p.setChecked(true);
                    break;
                }
            }
        }
        role.setPermsList(list);
        return role;
    }
}
