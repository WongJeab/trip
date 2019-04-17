package com.cn.platform.managecenter.service.log;


import com.cn.platform.managecenter.entity.log.OperateLog;
import com.cn.platform.managecenter.utils.TableResult;

import java.util.Map;

public interface OperateLogService {
      int  save(OperateLog operateLog);

      void logEventPublish(OperateLog operateLog);

      int insert(OperateLog operateLog);

      void deleteByPrimaryKey(Integer id);

      OperateLog selectByPrimaryKey(Integer id);

      void updateByPrimaryKey(OperateLog operateLog);

      TableResult queryOperateLogListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap);
}
