package com.cn.platform.managecenter.dao.def.system;


import java.util.List;
import java.util.Map;

public interface RolePermMapper {

    void bathAddRolePerm(List<Map<String, Object>> list);

    List<Integer> getPermIdByRoleId(Map<String, Object> map);

    void deletePermByRoleId(Integer roleId);

    void deleteRolePermByPermId(Integer permId);

}