package com.jayjay.ldst.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jayjay
 * @date 2019/12/1918:54
 * @Description: 分页类
 */
public class Page<T> {

    /**
     * 当前第几页
     */
    private int page = 1;

    /**
     * 每页记录数
     */
    private int pageSize = 10;

    /**
     * 记录总数
     */
    private int totalSize;

    /**
     * 排序规则：升序(asc) or 降序(desc)
     */
    private String dir = "asc";

    /**
     * 排序字段
     */
    private String sort = "id";

    /**
     * 业务对象集合
     */
    private List<T> collection = new ArrayList<T>();



    /**
     * 用于存放其他的数据（查询参数）
     */
    private Map<String, Object> map = new HashMap<String, Object>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<T> getCollection() {
        return collection;
    }

    public void setCollection(List<T> collection) {
        this.collection = collection;
    }
}
