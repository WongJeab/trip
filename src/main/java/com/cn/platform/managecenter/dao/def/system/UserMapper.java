package com.cn.platform.managecenter.dao.def.system;



import com.cn.platform.managecenter.entity.system.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    User getUserByParms(Map<String, Object> map);


    List<User> queryUserListPage(Map<String, Object> map);

    Long queryUserCount(Map<String, Object> map);

    Integer   saveUser(User user);

    void      updateUser(User user);

    void updateUserGroup(Map<String, Object> inMap);

    void updateLastLoginTime(Integer userId);

    List<User> selectByUserNameAndPhone(Map<String, Object> map);
}