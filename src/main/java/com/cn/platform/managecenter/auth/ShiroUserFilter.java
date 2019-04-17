package com.cn.platform.managecenter.auth;

import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.entity.system.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**权限访问控制filter
 * Created by dhj on 2018/7/31.
 */
public class ShiroUserFilter extends AccessControlFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        saveRequestAndRedirectToLogin(request, response);
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        User u= (User)SecurityUtils.getSubject().getSession().getAttribute(CommonConstant.USER_KEY);
        //Session未失效时验证通过
        if(null != u || isLoginRequest(request, response)){
            return Boolean.TRUE;
        }
         return  false;
    }
}
