package com.nowcoder.community.entity;

/**
 * 封装分页相关的信息.
 */
public class Page {


    /**
     * 当前页码
     */

    private int current = 1;

    /**
     * 每页数据量
     */

    private int limit = 10;

    /**
     * 总数据行数（数据库）
     */

    private int rows;

    /**
     * 页码跳转路径
     */
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit<=100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取到当前页的起始行（数据库）
     */

    public int getOffset() {
    //     当前页码current * 每页限制数据量limit - limit
        return (current - 1) * limit;
    }

    /**
     * 获取总页数
     */

    public int getTotal() {
        // 总数据量rows / 每页限制数量量limit
        // 需要考虑除不尽的情况
        int total = rows / limit;
        if (rows == 0) {
            return total;
        }
        return total + 1;
    }

    /**
     * 获取上一页页码
     */
    public int getFrom(){
        // 当前页-2 ：需要考虑当前页是首页的情况
        int from = current - 2;
        return from < 1 ? 1 : from;
    }


    /**
     * 跳转到后一页
     */

    public int getTO(){
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
