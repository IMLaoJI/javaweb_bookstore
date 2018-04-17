package com.laoji.bookstore.domain;

import java.util.List;

public class PageBean<T> {

    private int currentPage;//当前页 浏览器传递
    private int currentCount;//每页显示个数 固定值 也可以由浏览器传递
    private int totalCount; //总记录数 数据库查询
    private int totalPage; //总分页数
    private int startIndex;//开始索引
    private List<T> list; //分页数据 数据库查询

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public PageBean() {
        super();
    }

    public PageBean(int currentPage, int currentCount, int totalCount) {
        this.currentPage = currentPage;
        this.currentCount = currentCount;
        this.totalCount = totalCount;
        this.totalPage = this.totalCount % this.currentCount == 0 ? this.totalCount / this.currentCount
                : this.totalCount / this.currentCount + 1;
        this.startIndex = (this.currentPage-1)*this.currentCount;
    }
}
