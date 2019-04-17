package com.cn.platform.managecenter.entity;

/**
 * ajax 返回类
 * 
 * @author tamg
 */
public class AjaxVo {
	public static final int SUCCESS=1;
	public static final int ERROR=0;
	private int code=SUCCESS;
	private String msg;
	private Object obj;
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
