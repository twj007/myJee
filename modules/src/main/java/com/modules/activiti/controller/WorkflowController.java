package com.modules.activiti.controller;

import com.common.utils.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/01
 **/
@RestController
@RequestMapping("/sys/workflow")
@Api("流程管理")
public class WorkflowController {

    @Autowired
    private ManagementService managementService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("/list")
    @ApiOperation("分页显示流程实例")
    public ResultBody list(){return null;}

    @GetMapping("/findOne")
    @ApiOperation("查询流程实例详情")
    public ResultBody findOne(String eventId){return null;}

    @GetMapping("/cancel")
    @ApiOperation("取消流程")
    public ResultBody cancel(){return null;}

    @GetMapping("/start")
    @ApiOperation("开启流程")
    public ResultBody startWorkflow(List<Object> params){return null;}

    @GetMapping("/process")
    @ApiOperation("处理流程")
    public ResultBody process(){return null;}

    @GetMapping("/select/next")
    @ApiOperation("查询流程下一个处理人")
    public ResultBody list(String eventId){return null;}

    @GetMapping("/suspend")
    @ApiOperation("挂起，恢复流程实例")
    public ResultBody suspend(){return null;}

    @GetMapping("/deploy")
    @ApiOperation("部署流程实例")
    public ResultBody deploy(){return null;}

    @GetMapping("/tasks")
    @ApiOperation("获取流程任务")
    public ResultBody tasks(){return null;}

    @GetMapping("/runningFlow")
    @ApiOperation("获取部署流程")
    public ResultBody getRunningInstance(){return null;}

    @GetMapping("/giveTo")
    @ApiOperation("委托处理人处理")
    public ResultBody giveTo(){return null;}
}
