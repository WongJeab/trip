package com.cn.platform.managecenter.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class OutMsg<T> implements Serializable {
    private CodeEnum code;
    private String url;
    private String msg;
    private T data;

    public OutMsg(){}

    public OutMsg(CodeEnum code, String msg, String url, T data){
        this.code = code;
        this.msg = msg;
        this.url = url;
        this.data = data;
    }

    /**
     * 成功
     */
    public OutMsg<T> success(String msg, String url, T data){
        this.code = CodeEnum.SUCCESS;
        this.url = url;
        this.msg = msg;
        this.data = data;
        return this;
    }
    /**
     * 失败
     */
    public OutMsg<T> fail(String msg, String url, T data){
        this.code = CodeEnum.FAIL;
        this.url = url;
        this.msg = msg;
        this.data = data;
        return this;
    }
    /**
     * 错误
     */
    public OutMsg<T> error(String msg, String url, T data){
        this.code = CodeEnum.ERROR;
        this.url = url;
        this.msg = msg;
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public CodeEnum getCode() {
        return code;
    }

    public void setCode(CodeEnum code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 枚举Code
     */
    public enum CodeEnum {
        SUCCESS, FAIL, ERROR
    }
}
