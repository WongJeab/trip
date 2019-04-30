package com.cn.platform.managecenter.dao.def.log;


import com.cn.platform.managecenter.entity.log.OperateLog;

import java.util.List;
import java.util.Map;

public interface OperateLogMapper{

    int deleteByPrimaryKey(Integer id);

    int insert(OperateLog record);

    OperateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(OperateLog record);

    List<OperateLog> queryOperateLogsListPage(Map<String, Object> inMap);

    Long queryOperateLogConfigCount(Map<String, Object> inMap);
}