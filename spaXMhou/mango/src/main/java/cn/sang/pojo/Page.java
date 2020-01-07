package cn.sang.pojo;

import java.util.List;

public class Page {
    private int currentPageNum;//当前页
    private int pageSize=10;//每页显示的条数
    private int totalRecords;//总记录条数
    private int totalPageNum;//总页数
    private Object records;//分好页的结果集（最多只能有10个对象）

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        this.totalPageNum = totalRecords % this.pageSize == 0?(totalRecords / this.pageSize):(totalRecords / this.pageSize)+1;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public Object getRecords() {
        return records;
    }

    public void setRecords(Object records) {
        this.records = records;
    }
}
