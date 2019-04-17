package com.cn.platform.managecenter.service.system;



import com.cn.platform.managecenter.entity.system.Perms;
import com.cn.platform.managecenter.utils.TableResult;

import java.util.List;
import java.util.Map;

/**
 * Created by dhj on 2018/8/9.
 */
public interface PermsService {
    /**
     * 获取所有权限
     * @param map
     * @return
     */
    List<Perms> selectAllPerms(Map<String, Object> map);

    /**
     * 分页查询权限信息
     * @param pageNum
     * @param pageSize
     * @param inMap
     * @return
     */
    TableResult queryPermListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap);

    /**
     * 查询权限
     * @param inMap
     * @return
     */
    Perms getPermsByPerm(Map<String, Object> inMap);

    /**
     * 添加权限
     * @param perms
     */
    void  savePerms(Perms perms);

    /**
     * 更新权限
     * @param perms
     */
    void updatePerms(Perms perms);

    /**
     * 删除权限
     * @param permId
     */
    void deletePermByPermId(Integer permId);
}
