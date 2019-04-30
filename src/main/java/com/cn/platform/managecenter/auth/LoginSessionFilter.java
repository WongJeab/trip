package com.cn.platform.managecenter.auth;


import com.alibaba.fastjson.JSONObject;
import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.entity.system.User;
import com.cn.platform.managecenter.utils.Md5Util;
import com.cn.platform.managecenter.utils.OutMsg;
import com.cn.platform.managecenter.utils.ShiroUtil;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by dhj on 2018/7/30.
 */

public class LoginSessionFilter extends AdviceFilter {
    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        User sysUser = (User) httpServletRequest.getSession().getAttribute("user");
        String requestURI=httpServletRequest.getRequestURI();
        httpServletResponse.setCharacterEncoding("utf-8");
        String localUrl = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort()
                + httpServletRequest.getContextPath() + "/";
        if( requestURI.contains("actuator/health")){
            return true;
        }
        if (sysUser != null && ShiroUtil.sha256Hash(Md5Util.MD5Encode(CommonConstant.DEFAULT_PWD)).equals(sysUser.getPassword())) {
            if (requestURI.contains("system/user/updatePwdPage") || requestURI.contains("system/user/updateUserPwd")|| requestURI.contains("login")
            ||requestURI.contains("randomImg")|| requestURI.contains("static")) {
            }else{
                localUrl+= "system/user/updatePwdPage";
                httpServletResponse.setHeader("content-type", "text/html;charset=UTF-8");
                httpServletResponse.getWriter()
                        .write("<script>alert('密码太弱，请修改密码后操作!');window.location.href='" + localUrl + "'</script>");
            }
        }
        if (null == sysUser &&  !requestURI.contains("/login") && !requestURI.contains("/sendPhoneMsg")
                && !requestURI.contains("/validPhoneMsg")
            && isAjax(httpServletRequest) ) {//ajax会话失效
                httpServletResponse.setHeader("sessionstatus", "timeout"); // 响应头设置session状态
                httpServletResponse.setHeader("loginPath", localUrl + "login"); // 响应头设置session状态
                OutMsg<String> outMsg = new OutMsg<String>();
                outMsg.fail("登录会话超时,请重新登录!","","");
                httpServletResponse.getWriter().write(JSONObject.toJSONString(outMsg));
            System.out.println("ajax session time");
                return false;
        }

        return true;
    }


    /**
     *是否是Ajax请求,如果是ajax请求响应头会有，x-requested-with
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"));
    }


}
