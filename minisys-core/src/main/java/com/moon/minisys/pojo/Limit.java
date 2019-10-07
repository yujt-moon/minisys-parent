package com.moon.minisys.pojo;

/**
 * 分页实体类
 * @author yujiangtao
 * @date 2018/5/9 17:44
 */
public class Limit {

    /**
     * 第一页的页码
     */
    public static final int FIRST_PAGE = 1;

    /**
     * 默认每页记录数--10
     */
    public static final int DEFAULT_SIZE = 10;

    /**
     * 每页的记录数
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer pageNum;

    public Limit() {}

    public Limit(Integer pageNum) {
        this(pageNum, DEFAULT_SIZE);
    }

    public Limit(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageSize() {
        if(this.pageSize == null) {
            return DEFAULT_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        if(this.pageNum == null) {
            return FIRST_PAGE;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
