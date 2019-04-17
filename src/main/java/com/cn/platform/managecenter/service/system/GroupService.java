package com.cn.platform.managecenter.service.system;

import com.cn.platform.managecenter.entity.system.Group;
import com.cn.platform.managecenter.utils.TableResult;

import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/03/25 10:50
 */
public interface GroupService {
    /**
     * 查询分组信息
     * @param map
     * @return
     */
    List<Group> getGroupByParms(Map<String, Object> map);

    Group queryGroupById(int id);
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    TableResult queryGroupListPage(Integer pageNum, Integer pageSize, Map<String, Object> map);
    /**
     * 添加渠道
     * @param group
     */
    void   addGroup(Group group);

    /**
     * 更新渠道
     * @param group
     */
    void   updateGroup(Group group);

    /**
     * 启用停用
     * @param group
     */
    void  updateGroupStatus(Group group);

    List<Group> queryGroupListAll();

    void deleteGroup(int id);

    String getGroupSql(int id);

}
