package com.cn.platform.managecenter.dao.def.system;


import java.util.List;
import java.util.Map;

public interface UserRoleMapper {

    /**
     * 批量添加用户角色
     * @param list
     */
   void  bathAddUserRole(List<Map<String, Object>> list);

    /**
     * 删除用户或者角色关系
     * @param map
     */
    void deleteUserRoleByParms(Map<String, Object> map);

}