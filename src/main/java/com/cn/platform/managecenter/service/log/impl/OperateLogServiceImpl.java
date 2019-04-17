package com.cn.platform.managecenter.service.log.impl;

import com.cn.platform.managecenter.dao.def.log.OperateLogMapper;
import com.cn.platform.managecenter.entity.log.OperateLog;
import com.cn.platform.managecenter.service.log.OperateLogService;
import com.cn.platform.managecenter.utils.TableResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional("defTransactionManager")

public class OperateLogServiceImpl implements OperateLogService {
    private static Logger logger = LoggerFactory.getLogger(OperateLogServiceImpl.class);

    @Resource
    private OperateLogMapper operateLogMapper;

    @Override
    public int save(OperateLog operateLog) {
        return operateLogMapper.insert(operateLog);
    }

    @EventListener
    @Async
    @Override
    public void logEventPublish(OperateLog operateLog){
        save(operateLog);
    }

    @Override
    public int insert(OperateLog operateLog) {
        return operateLogMapper.insert(operateLog);
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        operateLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OperateLog selectByPrimaryKey(Integer id) {
        return operateLogMapper.selectByPrimaryKey(id);
    }
    @Override
    public void updateByPrimaryKey(OperateLog operateLog) {
        operateLogMapper.updateByPrimaryKey(operateLog);
    }

    @Override
    public TableResult queryOperateLogListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap) {
        inMap.put("pageStart",(pageNum-1)*pageSize);
        inMap.put("pageEnd",pageSize);
        List<OperateLog> list=operateLogMapper.queryOperateLogsListPage(inMap);
        Long total=operateLogMapper.queryOperateLogConfigCount(inMap);
        TableResult  tableResult = new TableResult(pageNum,total,list);
        return tableResult;
    }
}
