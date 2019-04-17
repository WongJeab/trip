package com.cn.platform.managecenter.dao.def.system;




import com.cn.platform.managecenter.entity.system.Perms;

import java.util.List;
import java.util.Map;

public interface PermsMapper {

    List<Perms> selectAllPerms(Map<String, Object> map);

    List<Perms> getPermsByUserId(Integer userId);

    List<Perms> getPermsByRoleId(Map<String, Object> inMap);

    List<Perms> queryPermListPage(Map<String, Object> inMap);

    Long queryPermCount(Map<String, Object> inMap);

    Perms getPermsByParms(Map<String, Object> inMap);

    void savePerms(Perms perms);

    void updatePerms(Perms perms);

   void   deletePermsByPermId(Integer permId);
}