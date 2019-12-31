package com.modules.mybatis.modal;

import java.io.Serializable;

public class TaskError implements Serializable {
    private Long id;

    private String taskexecuterecordid;

    private String errorkey;

    private Long createtime;

    private Long lastmodifytime;

    private String errorvalue;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskexecuterecordid() {
        return taskexecuterecordid;
    }

    public void setTaskexecuterecordid(String taskexecuterecordid) {
        this.taskexecuterecordid = taskexecuterecordid == null ? null : taskexecuterecordid.trim();
    }

    public String getErrorkey() {
        return errorkey;
    }

    public void setErrorkey(String errorkey) {
        this.errorkey = errorkey == null ? null : errorkey.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Long lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getErrorvalue() {
        return errorvalue;
    }

    public void setErrorvalue(String errorvalue) {
        this.errorvalue = errorvalue == null ? null : errorvalue.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskexecuterecordid=").append(taskexecuterecordid);
        sb.append(", errorkey=").append(errorkey);
        sb.append(", createtime=").append(createtime);
        sb.append(", lastmodifytime=").append(lastmodifytime);
        sb.append(", errorvalue=").append(errorvalue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}