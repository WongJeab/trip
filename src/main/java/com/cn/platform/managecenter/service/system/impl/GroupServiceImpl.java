package com.cn.platform.managecenter.service.system.impl;

import com.alibaba.fastjson.JSON;
import com.cn.platform.managecenter.dao.def.system.GroupMapper;
import com.cn.platform.managecenter.entity.system.Group;
import com.cn.platform.managecenter.service.system.GroupService;
import com.cn.platform.managecenter.utils.TableResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: wangyingxian
 * Date: 2019/03/25 10:59
 */
@Service
@Transactional("defTransactionManager")
public class GroupServiceImpl implements GroupService {
    private static Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Resource
    GroupMapper groupMapper;

    @Override
    public List<Group> getGroupByParms(Map<String, Object> map) {
        return groupMapper.getGroupByParms(map);
    }

    @Override
    public Group queryGroupById(int id) {
        return groupMapper.queryGroupById(id);
    }

    @Override
    public TableResult queryGroupListPage(Integer pageNum, Integer pageSize, Map<String, Object> inMap) {
        inMap.put("pageStart",(pageNum-1)*pageSize);
        inMap.put("pageEnd",pageSize);
        List<Group> pageList= groupMapper.queryGroupListPage(inMap);
        Long  total= groupMapper.queryGroupCount(inMap);
        TableResult tableResult = new TableResult(pageNum,total,pageList);
        return tableResult;
    }

    @Override
    public void addGroup(Group group) {
        groupMapper.addGroup(group);
    }

    @Override
    public void updateGroup(Group group) {
        Group t_group = groupMapper.queryGroupById(group.getId());
        if(null!=t_group){
            if(StringUtils.isNotBlank(group.getStatus())){
                t_group.setStatus(group.getStatus());
                groupMapper.updateGroup(group);
            }else{
                t_group.setName(group.getName());
                t_group.setPId(group.getPId());
                t_group.setRemark(group.getRemark());
                groupMapper.updateGroup(t_group);
            }
        }
    }

    @Override
    public void updateGroupStatus(Group group) {
        groupMapper.updateGroup(group);
    }

    @Override
    public List<Group> queryGroupListAll() {
        return groupMapper.queryGroupListAll();
    }

    @Override
    public void deleteGroup(int id) {
        groupMapper.deleteGroup(id);
    }

    @Override
    public String getGroupSql(int groupId) {
        // 格式 1,2,3
        Set<Integer> groupSet = new HashSet<>();
        Set<Integer> groupSetTemp = new HashSet<>();
        Set<Integer> groupSetTempZero = new HashSet<>();
        List<Group> groupList = groupMapper.queryGroupListAll();
        groupSet.add(groupId);
        groupSetTempZero.add(groupId);
        groupSetTemp.add(groupId);
        boolean bool = true;
        while(bool){
            for (Integer groupTemp:groupSetTemp) {
                for (Group channel:groupList) {
                    if(null!=channel&&null!=channel.getPId()){
                        if(groupTemp.equals(channel.getPId())){
                            groupSetTempZero.add(channel.getId());
                            groupSet.add(channel.getId());
                        }
                    }
                }
            }
            groupSetTemp.clear();
            //channelSetTemp = channelSetTempZero;//数据赋值不可用
            for (int channelTempZero:groupSetTempZero) {
                groupSetTemp.add(channelTempZero);
            }
            if(groupSetTempZero.isEmpty() || groupSetTempZero.size()<1){
                bool=false;
            }
            groupSetTempZero.clear();
        }
        //logger.info(JSON.toJSONString(channelSet));
        String channelString =  JSON.toJSONString(groupSet);
        String channelChildIds =channelString.substring(1,channelString.length()-1);
        String  sessionSql =  "in ("+channelChildIds+")";
        return sessionSql;
    }

}
