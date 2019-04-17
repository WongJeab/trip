package com.cn.platform.managecenter.service.system;



import com.cn.platform.managecenter.entity.system.Role;
import com.cn.platform.managecenter.utils.TableResult;

import java.util.List;
import java.util.Map;

/**
 * Created by dhj on 2018/8/13.
 */
public interface RoleService {
    /**
     * 获取所有角色
     * @param map
     * @return
     */
    List<Role> getAllRoles(Map<String, Object> map);

    /**
     * 分页查询角色信息
     * @param pageNum
     * @param pageSize
     * @param inMap
     * @return
     */
    TableResult queryRoleListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap);

    /**
     * 查询角色信息
     * @param map
     * @return
     */
    Role getRoleByRole(Map<String, Object> map);

    /**
     * 添加角色
     * @param role
     * @param permIds
     */
    void saveRole(Role role, String[] permIds);

    /**
     * 更新角色
     * @param role
     * @param permIds
     */
    void updateRole(Role role, String[] permIds);

    /**
     * 删除角色
     * @param role
     */
    void deleteRoleByRoleId(Role role);

    /**
     * 获取角色权限
     * @param inMap
     * @return
     */
    Role getRolePerms(Map<String, Object> inMap);

}
