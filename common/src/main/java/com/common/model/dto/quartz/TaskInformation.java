package com.common.model.dto.quartz;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class TaskInformation implements Serializable {

    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "版本")
    private Integer version;

    @ApiModelProperty("job编号")
    private String taskno;

    @ApiModelProperty("job描述")
    private String taskname;

    @ApiModelProperty("cron表达式")
    private String schedulerrule;

    @ApiModelProperty("冻结状态")
    private String frozenstatus;

    @ApiModelProperty("执行方")
    private String executorno;

    @ApiModelProperty("冻结时间")
    private Long frozentime;

    @ApiModelProperty("解冻时间")
    private Long unfrozentime;

    @ApiModelProperty("创建时间")
    private Long createtime;

    @ApiModelProperty("最后修改时间")
    private Long lastmodifytime;

    @ApiModelProperty(name = "发送类型", example = "http, kafka")
    private String sendtype;

    @ApiModelProperty(name = "执行请求路径", example = "http://localhost:8080/test")
    private String url;

    @ApiModelProperty(name = "执行参数", example = "123")
    private String executeparamter;

    @ApiModelProperty(name = "timekey")
    private String timekey;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTaskno() {
        return taskno;
    }

    public void setTaskno(String taskno) {
        this.taskno = taskno == null ? null : taskno.trim();
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }

    public String getSchedulerrule() {
        return schedulerrule;
    }

    public void setSchedulerrule(String schedulerrule) {
        this.schedulerrule = schedulerrule == null ? null : schedulerrule.trim();
    }

    public String getFrozenstatus() {
        return frozenstatus;
    }

    public void setFrozenstatus(String frozenstatus) {
        this.frozenstatus = frozenstatus == null ? null : frozenstatus.trim();
    }

    public String getExecutorno() {
        return executorno;
    }

    public void setExecutorno(String executorno) {
        this.executorno = executorno == null ? null : executorno.trim();
    }

    public Long getFrozentime() {
        return frozentime;
    }

    public void setFrozentime(Long frozentime) {
        this.frozentime = frozentime;
    }

    public Long getUnfrozentime() {
        return unfrozentime;
    }

    public void setUnfrozentime(Long unfrozentime) {
        this.unfrozentime = unfrozentime;
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

    public String getSendtype() {
        return sendtype;
    }

    public void setSendtype(String sendtype) {
        this.sendtype = sendtype == null ? null : sendtype.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getExecuteparamter() {
        return executeparamter;
    }

    public void setExecuteparamter(String executeparamter) {
        this.executeparamter = executeparamter == null ? null : executeparamter.trim();
    }

    public String getTimekey() {
        return timekey;
    }

    public void setTimekey(String timekey) {
        this.timekey = timekey == null ? null : timekey.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", taskno=").append(taskno);
        sb.append(", taskname=").append(taskname);
        sb.append(", schedulerrule=").append(schedulerrule);
        sb.append(", frozenstatus=").append(frozenstatus);
        sb.append(", executorno=").append(executorno);
        sb.append(", frozentime=").append(frozentime);
        sb.append(", unfrozentime=").append(unfrozentime);
        sb.append(", createtime=").append(createtime);
        sb.append(", lastmodifytime=").append(lastmodifytime);
        sb.append(", sendtype=").append(sendtype);
        sb.append(", url=").append(url);
        sb.append(", executeparamter=").append(executeparamter);
        sb.append(", timekey=").append(timekey);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}