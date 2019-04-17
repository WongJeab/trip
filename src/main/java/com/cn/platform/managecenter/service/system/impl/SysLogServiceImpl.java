package com.cn.platform.managecenter.service.system.impl;

import com.cn.platform.managecenter.dao.def.system.SysLogMapper;
import com.cn.platform.managecenter.entity.system.SysLog;
import com.cn.platform.managecenter.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by dhj on 2018/8/22.
 */
@Service
@Qualifier("sysLogService")
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;
    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.save(sysLog);
    }
}
