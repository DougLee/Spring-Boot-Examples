package com.example.springbootshiro.domain;

import java.io.Serializable;

/**
 * Created by Douglee on 2018/4/2.
 */
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -8510120969167698570L;

    private int pageSize;

    private int pageNum;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
