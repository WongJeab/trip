package com.cn.platform.managecenter.service.system.impl;


import com.cn.platform.managecenter.dao.def.system.PermsMapper;
import com.cn.platform.managecenter.dao.def.system.RolePermMapper;
import com.cn.platform.managecenter.entity.system.Perms;
import com.cn.platform.managecenter.service.system.PermsService;
import com.cn.platform.managecenter.utils.TableResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by dhj on 2018/8/9.
 */
@Service
@Qualifier("permsService")
@Transactional
public class PermsServiceImpl implements PermsService {
    @Resource
    private PermsMapper permsMapper;
    @Resource
    private RolePermMapper rolePermMapper;
    @Override
    public List<Perms> selectAllPerms(Map<String,Object> map) {
        return permsMapper.selectAllPerms(map);
    }
    @Override
    public TableResult queryPermListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap) {

        inMap.put("pageStart",(pageNum-1)*pageSize);
        inMap.put("pageEnd",pageSize);
        List<Perms> permsList= permsMapper.queryPermListPage(inMap);
        Long  total= permsMapper.queryPermCount(inMap);
        TableResult<Perms> tableResult = new TableResult(pageNum,total,permsList);
        return tableResult;
    }

    @Override
    public Perms getPermsByPerm(Map<String, Object> inMap) {

        return permsMapper.getPermsByParms(inMap);
    }

    @Override
    public void savePerms(Perms perms) {

        permsMapper.savePerms(perms);
    }

    @Override
    public void updatePerms(Perms perms) {

        permsMapper.updatePerms(perms);

    }

    @Override
    public void deletePermByPermId(Integer permId) {

        permsMapper.deletePermsByPermId(permId);

        rolePermMapper.deleteRolePermByPermId(permId);

    }


}
