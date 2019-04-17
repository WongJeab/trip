package com.cn.platform.managecenter.dao.def.system;




import com.cn.platform.managecenter.entity.system.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    List<Role> getRolesByUserId(Integer userId);

    List<Role> getAllRoles(Map<String, Object> map);


    List<Role> queryRoleListPage(Map<String, Object> inMap);

    Long queryRoleCount(Map<String, Object> inMap);

    Role getRoleByParms(Map<String, Object> map);

    void saveRole(Role role);

    void  updateRole(Role role);

    void deleteByRoleId(Role role);
}