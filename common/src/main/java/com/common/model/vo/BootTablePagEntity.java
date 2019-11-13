package com.common.model.vo;

import java.io.Serializable;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/12
 **/
public class BootTablePagEntity<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
    ** 对应页面总条数
    **/
    private long total;
    /**
     * 对应页面结果集
     */
    private List<T> rows;

    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}

