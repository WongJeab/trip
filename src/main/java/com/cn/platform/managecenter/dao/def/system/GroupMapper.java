package com.cn.platform.managecenter.dao.def.system;


import com.cn.platform.managecenter.entity.system.Group;

import java.util.List;
import java.util.Map;

/**
 * User: wangyingxian
 * Date: 2019/03/25 10:56
 */
public interface GroupMapper {

    List<Group> getGroupByParms(Map<String, Object> map);

    Group queryGroupById(int id);

    List<Group> queryGroupListPage(Map<String, Object> inMap);

    Long queryGroupCount(Map<String, Object> inMap);

    void   addGroup(Group Group);

    void   updateGroup(Group Group);

    void deleteGroup(int id);

    List<Group> queryGroupListAll();

}
