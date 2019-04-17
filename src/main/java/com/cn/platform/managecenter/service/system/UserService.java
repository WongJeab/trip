package com.cn.platform.managecenter.service.system;




import com.cn.platform.managecenter.entity.system.User;
import com.cn.platform.managecenter.utils.TableResult;

import java.util.List;
import java.util.Map;

/**
 * Created by dhj on 2018/8/8.
 */
public interface UserService {
    /**
     * 查询用户信息
     * @param map
     * @return
     */

    User getUserByParms(Map<String, Object> map);

    /**
     * 分页查询用户列表信息
     * @param pageNum
     * @param pageSize
     * @param map
     * @return
     */
    TableResult queryUserListPage(Integer pageNum, Integer pageSize, Map<String, Object> map);

    /**
     * 添加用户
     * @param user
     * @param roleIds
     */
     void   saveUser(User user, String[] roleIds);

    /**
     * 更新用户
     * @param user
     * @param roleIds
     */
    void   updateUserInfo(User user, String[] roleIds);

    /**
     * 启用停用用户
     * @param u
     */
    void  updateUserStatus(User u);

    /**
     * 获取用户角色信息
     * @param map
     * @return
     */
    User getUserAndRoles(Map<String, Object> map);

    /**
     * 用户登录时间设置
     * @param userId
     */
    void updateLastLoginTime(Integer userId);

    /**
     * 修改密码
     * @param user
     */
    void updateUserPwd(User user);


    List<User> getUserByUserNameAndPhone(Map<String, Object> map);


    /**
     * 修改分组
     * @param inMap
     */
    void updateUserGroup(Map<String, Object> inMap);
}
