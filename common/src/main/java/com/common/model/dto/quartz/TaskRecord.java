package com.common.model.dto.quartz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class TaskRecord implements Serializable {
    @ApiModelProperty
    private Long id;

    @ApiModelProperty
    private String taskno;

    @ApiModelProperty
    private String timekeyvalue;

    @ApiModelProperty
    private Long executetime;

    @ApiModelProperty
    private String taskstatus;

    @ApiModelProperty
    private Integer failcount;

    @ApiModelProperty
    private String failreason;

    @ApiModelProperty
    private Long createtime;

    @ApiModelProperty
    private Long lastmodifytime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskno() {
        return taskno;
    }

    public void setTaskno(String taskno) {
        this.taskno = taskno == null ? null : taskno.trim();
    }

    public String getTimekeyvalue() {
        return timekeyvalue;
    }

    public void setTimekeyvalue(String timekeyvalue) {
        this.timekeyvalue = timekeyvalue == null ? null : timekeyvalue.trim();
    }

    public Long getExecutetime() {
        return executetime;
    }

    public void setExecutetime(Long executetime) {
        this.executetime = executetime;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus == null ? null : taskstatus.trim();
    }

    public Integer getFailcount() {
        return failcount;
    }

    public void setFailcount(Integer failcount) {
        this.failcount = failcount;
    }

    public String getFailreason() {
        return failreason;
    }

    public void setFailreason(String failreason) {
        this.failreason = failreason == null ? null : failreason.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskno=").append(taskno);
        sb.append(", timekeyvalue=").append(timekeyvalue);
        sb.append(", executetime=").append(executetime);
        sb.append(", taskstatus=").append(taskstatus);
        sb.append(", failcount=").append(failcount);
        sb.append(", failreason=").append(failreason);
        sb.append(", createtime=").append(createtime);
        sb.append(", lastmodifytime=").append(lastmodifytime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}