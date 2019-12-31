package com.jee.workflow.controller;

import com.common.constant.CommonConstants;
import com.common.model.workflow.vo.ProcessDefinationVo;
import com.common.utils.EncryptUtils;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
@Slf4j
public class WorkflowController extends BaseController{




    @GetMapping("/list")
    @ApiOperation("分页显示流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第一条数据的位置"),
            @ApiImplicitParam(name =  "pageSize", value = "页数据量")
    })
    public ResultBody list(int pageNum, int pageSize){
        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().listPage(pageNum, pageSize);
        List<ProcessDefinationVo> definitionVos = new ArrayList<>();
        for(ProcessDefinition definition : definitions){
            ProcessDefinationVo vo = new ProcessDefinationVo();
            BeanUtils.copyProperties(definition, vo);
            definitionVos.add(vo);
        }
        return Results.SUCCESS.result("success", definitionVos);
    }

    @GetMapping("/findOne")
    @ApiOperation("查询流程部署详情(最近一次)")
    @ApiImplicitParam(name = "deployId", value = "流程部署id", allowEmptyValue = false)
    public ResultBody findOne(String deployId){
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(deployId).latestVersion().singleResult();
        ProcessDefinationVo vo = new ProcessDefinationVo();
        BeanUtils.copyProperties(definition, vo);
        return Results.SUCCESS.result("success", vo);
    }



    @GetMapping("/tasks")
    @ApiOperation("获取流程任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventId", value = "事务号"),
            @ApiImplicitParam(name = "request", value = "获取请求头")
    })
    public ResultBody tasks(@RequestParam(value = "eventId", required = false) String eventId, HttpServletRequest request, int pageNum, int pageSize){
        String token = request.getHeader(CommonConstants.X_ACCESS_TOKEN);
        String username = EncryptUtils.getIssuer(token);
        boolean isEventIdEmpty = StringUtils.isEmpty(eventId);
        List<HistoricTaskInstance> links = null;

        if(isEventIdEmpty ){
            links = historyService.createHistoricTaskInstanceQuery().taskAssignee(username).processFinished().listPage(pageNum, pageSize);
        }else{
            links = historyService.createHistoricTaskInstanceQuery().taskAssignee(username).processFinished().processInstanceId(eventId).listPage(pageNum, pageSize);
        }

        return Results.SUCCESS.result("获取成功", links);
    }

}
