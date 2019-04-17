package com.cn.platform.managecenter.controller;

import com.cn.platform.managecenter.constant.CommonConstant;
import com.cn.platform.managecenter.entity.system.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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



}
