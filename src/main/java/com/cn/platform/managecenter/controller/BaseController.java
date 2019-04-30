package com.cn.platform.managecenter.controller;

import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.entity.system.User;
import com.cn.platform.managecenter.entity.wx.WxLoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;



	@ModelAttribute
	public void initMode(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public   User getSession(){
		Subject subject = SecurityUtils.getSubject();
        return  (User) subject.getSession().getAttribute(CommonConstant.USER_KEY);
	}

	public void  removeUserSession() {
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().removeAttribute(CommonConstant.USER_KEY);
	}

	public Map<String,String> getRequestHeader(){
		Map<String,String> headerMap =  new HashMap<>();
		headerMap.put("loginToken",request.getHeader("loginToken"));
		headerMap.put("apiToken",request.getHeader("apiToken"));
		headerMap.put("apiSecret",request.getHeader("apiSecret"));
		return headerMap;
	}


}
