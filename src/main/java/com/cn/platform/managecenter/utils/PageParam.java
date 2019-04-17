package com.cn.platform.managecenter.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class PageParam {
    /**
     * 页码，等几页
     */
    private  Integer pageNum;
    /**
     * 每页要显示的数据条数
     */
    private Integer pageSize;
    /**
     * 要排序的字段
     */
    private String sort;
    /**
     * 排序规则
     */
    private String sortOrder;
    /**
     * 额外添加的参数
     */
    private Map<String,Object> otherParams;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Map<String, Object> getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(Map<String, Object> otherParams) {
        this.otherParams = otherParams;
    }
}
