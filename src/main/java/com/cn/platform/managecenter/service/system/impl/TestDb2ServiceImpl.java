package com.cn.platform.managecenter.service.system.impl;



import com.cn.platform.managecenter.dao.def.system.TestDao;
import com.cn.platform.managecenter.dao.test.TestDb2Dao;
import com.cn.platform.managecenter.service.system.TestDb2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dhj on 2018/8/2.
 */
@Service
public class TestDb2ServiceImpl implements TestDb2Service {
    @Autowired
    private TestDao testDb2Dao;
    @Override
    public List getAll() {
        return testDb2Dao.getAll();
    }
}
