package com.cn.platform.managecenter.auth;

import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.entity.system.Perms;
import com.cn.platform.managecenter.entity.system.Role;
import com.cn.platform.managecenter.entity.system.User;
import com.cn.platform.managecenter.service.system.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuthRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private UserService userService;

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {




        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        if(StringUtils.isBlank(username)){//账户不存在
            throw new UnknownAccountException();
        }
        Map<String,Object> inMap= new HashMap<>();
        inMap.put("userName",username);
        inMap.put("userType", CommonConstant.CONTROL_DATA_TYPE);
        User user = userService.getUserByParms(inMap);
        if(null == user){      //帐号不存在
            throw new UnknownAccountException();
        }else if(!"E".equals(user.getStatus())){ //帐号被禁用;
            throw new DisabledAccountException();
        }
        userService.updateLastLoginTime(user.getUserId());
        //放入shiro.调用CredentialsMatcher.doCredentialsMatch方法检验密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());
        return simpleAuthenticationInfo;

    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        //获取session中的用户
       User user=(User) principal.fromRealm(this.getClass().getName()).iterator().next();
        List<Role> roleList = user.getRoleList();
        if(roleList!=null && roleList.size()>0){
            for(Role r:roleList){
                simpleAuthorizationInfo.addRole(r.getRole());
            }
        }
        List<Perms> permsList= user.getPermsList();
        if(permsList!=null && permsList.size()>0){
            for(Perms p:permsList){
                simpleAuthorizationInfo.addStringPermission(p.getPerm());
            }
        }
        return simpleAuthorizationInfo;
    }

}
