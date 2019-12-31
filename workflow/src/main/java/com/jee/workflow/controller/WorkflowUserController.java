package com.jee.workflow.controller;

import com.common.constant.CommonConstants;
import com.common.model.vo.system.SysUser;
import com.common.model.workflow.vo.BusinessTaskVo;
import com.common.model.workflow.vo.HistoricInstanceVo;
import com.common.utils.EncryptUtils;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.jee.workflow.component.DeleteTaskCmd;
import com.jee.workflow.component.SetFLowNodeAndGoCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/04
 **/
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/sys/workflow/user")
@Api("用户工作流处理接口")
@Slf4j
public class WorkflowUserController extends BaseController{


    @Value("${sys.rest.findUserByRole}")
    private String findUserByRole;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/start")
    @ApiOperation(value = "开启流程", notes = "参数格式： 1.流程部署key， 2.业务数据主键， 3.用于流程判断的参数 4.相关业务url 5.相关备注")
    @ApiImplicitParam(name = "params", value = "通用传递参数")
    public ResultBody startWorkflow(@RequestParam("params") ArrayList<String> params, HttpServletRequest request){
        String username = getUserByRequest(request);
        if(params.size() < CommonConstants.MINIUIMSIZE){
            return Results.BAD__REQUEST.result("缺少必要参数", null);
        }
        HashMap<String, Object> condition = new HashMap<>(16);
        condition.put("money", params.get(1));
        if(repositoryService.createProcessDefinitionQuery().processDefinitionKey(params.get(0)).latestVersion().singleResult().isSuspended()){
            return Results.BAD__REQUEST.result("该流程已挂起，请先联系激活此流程", null);
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey( params.get(0), condition);
        String processId = processInstance.getId();
        List<SysUser> users = startUserTask(processId, username);
        if(users == null){
            return Results.BAD__REQUEST.result("未找到处理人，流程事务已取消", null);
        }else{
            return Results.SUCCESS.result("流程创建成功，流程事务号为：" + processId, null);
        }
    }

    @GetMapping("/process")
    @ApiOperation("处理流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventId", value = "流程实例id"),
            @ApiImplicitParam(name = "pass", value = "处理意见"),
            @ApiImplicitParam(name = "request", value = "用于获取签收人任务")
    })
    public ResultBody process(String eventId, String pass, HttpServletRequest request){
        String username = getUserByRequest(request);
        Task task = taskService.createTaskQuery().processInstanceId(eventId).taskAssignee(username).singleResult();
        if(task == null){
            return Results.SUCCESS.result("当前事务已完成，无需处理", null);
        }else{
            //完成该事务
            taskService.setVariable(task.getId(), "pass", pass);
            taskService.complete(task.getId());
            //查找下一个处理人
            return list(eventId);
        }
    }

    @ApiOperation("查询流程下一个处理人,串行流程使用")
    @ApiImplicitParam(name = "eventId", value = "事务号")
    @RequestMapping("/selectNextUser")
    public ResultBody list(String eventId){
        Task task = taskService.createTaskQuery().processInstanceId(eventId).singleResult();
        if(task == null){
            return Results.SUCCESS.result(CommonConstants.FINISHED, null);
        }else{
            String taskId = task.getTaskDefinitionKey();
            ResultBody result = restTemplate.getForObject(findUserByRole+"?taskId="+taskId, ResultBody.class);
            List<SysUser> users;
            if(result.getCode() == HttpStatus.OK.value()){
                users = (List<SysUser>) result.getBody();
            }else{
                log.error("[/selectNextUser] errors when rpc findUserByRole {}", result.getMsg());
                return Results.BAD__REQUEST.result("查询处理人时发生异常", null);
            }
            if(users == null || users.size() == 0){
                runtimeService.deleteProcessInstance(eventId, CommonConstants.NOBODY);
                return Results.BAD__REQUEST.result(CommonConstants.NOBODY, null);
            }else{
                return Results.SUCCESS.result(CommonConstants.SUCCESS, users);
            }
        }
    }


    @ApiOperation("获取并启动发起人任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "事务号", value = "eventId"),
            @ApiImplicitParam(name = "username", value = "发起人id")
    })
    public List<SysUser> startUserTask(String eventId, String username){
        Task task = taskService.createTaskQuery().processInstanceId(eventId).singleResult();
        if(task == null){
            return null;
        }else{
            String taskId = task.getTaskDefinitionKey();
            List<SysUser> users = new ArrayList<>();
            if(username != null){
                if(CommonConstants.START.equals(taskId)){
                    taskService.setAssignee(task.getId(), username);
                    taskService.complete(task.getId());
                    SysUser user = new SysUser();
                    user.setUsername(username);
                    users.add(user);
                }else{
                    ResultBody result = restTemplate.getForObject(findUserByRole+"?taskId="+taskId, ResultBody.class);
                    if(result.getCode() == HttpStatus.OK.value()){
                        users = (List<SysUser>) result.getBody();
                    }else{
                        log.error("[/selectNextUser] errors when rpc findUserByRole {}", result.getMsg());
                        return null;
                    }
                }
            }
            if(users == null || users.size() == 0){
                runtimeService.deleteProcessInstance(eventId, CommonConstants.NOBODY);
                return null;
            }else{
                return users;
            }
        }
    }

    @GetMapping("/assign")
    @ApiOperation("将task签发给处理人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventId", value = "事务号"),
            @ApiImplicitParam(name =  "username", value = "处理人id（就是用户名）")
    })
    public ResultBody assign(String eventId, String username){
        Task task = taskService.createTaskQuery().processInstanceId(eventId).singleResult();
        taskService.setAssignee(task.getId(), username);
        return Results.SUCCESS.result(CommonConstants.SUCCESS, "处理成功");
    }

    @GetMapping("/cancel")
    @ApiOperation("取消流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventIds", value = "事务号"),
            @ApiImplicitParam(name = "reason", value = "取消原因")
    })
    public ResultBody cancel(@RequestParam("eventIds") List<String> eventIds, @RequestParam(name = "reason", required = false) String reason){
        int counts = 0;
        List<String> failList = new ArrayList<>();
        for(String eventId : eventIds){
            //  1.清除业务数据中的状态
            //  2.查询实例存不存在
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(eventId).singleResult();
            if(processInstance == null){
                failList.add(eventId);
                continue;
            }
            runtimeService.deleteProcessInstance( eventId, reason);
            counts ++;
        }
        return Results.SUCCESS.result("处理成功，共"+eventIds.size()+"条， 取消成功：" + counts + "条", failList);
    }

    @RequestMapping("/todo")
    @ApiOperation("获取待办")
    public ResultBody todo(HttpServletRequest request, int pageSize, int pageNum){
        String userId = getUserByRequest(request);
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userId).listPage(pageNum, pageSize);
        List<BusinessTaskVo> taskVos = new ArrayList<>();
        for(Task task : taskList){
            BusinessTaskVo taskVo = new BusinessTaskVo();
            BeanUtils.copyProperties(task, taskVo);
            taskVos.add(taskVo);
        }
        return Results.SUCCESS.result("success", taskVos);
    }

    @RequestMapping("/done")
    @ApiOperation("获取已办和我的事务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "分页大小"),
            @ApiImplicitParam(name = "pageNum", value = "起始位置"),
            @ApiImplicitParam(name = "type", value = "1， self获取自己发起的事务 2， assign获取自己处理的事务")
    })
    public ResultBody done(HttpServletRequest request, int pageSize, int pageNum, int type){
        String userId = getUserByRequest(request);
        List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).listPage(pageNum, pageSize);
        List<HistoricInstanceVo> taskListVo = new ArrayList<>();
        // 获取我的事务
        if(CommonConstants.SELF.equals(type)){
            for(HistoricTaskInstance instance : taskList){
                if(CommonConstants.START.equals(instance.getTaskDefinitionKey())){
                    HistoricInstanceVo vo = new HistoricInstanceVo();
                    BeanUtils.copyProperties(instance, vo);
                    taskListVo.add(vo);
                }
            }
        }
        // 获取已办事务
        if(CommonConstants.ASSIGN.equals(type)){
            for(HistoricTaskInstance instance : taskList){
                if(!CommonConstants.START.equals(instance.getTaskDefinitionKey())){
                    HistoricInstanceVo vo = new HistoricInstanceVo();
                    BeanUtils.copyProperties(instance, vo);
                    taskListVo.add(vo);
                }
            }
        }
        return Results.SUCCESS.result(CommonConstants.SUCCESS, taskListVo);
    }

    @GetMapping("/frontTask")
    @ApiOperation("获取前面结点的任务")
    public ResultBody frontBack(String eventId){
        List<HistoricActivityInstance> instances =  historyService.createHistoricActivityInstanceQuery().processInstanceId(eventId).list();
        Iterator iterator = instances.iterator();
        while(iterator.hasNext()){
            HistoricActivityInstance instance = (HistoricActivityInstance) iterator.next();
            if(!CommonConstants.USERTASK.equals(instance.getActivityType())){
                iterator.remove();
            }
        }
        return Results.SUCCESS.result(CommonConstants.SUCCESS, instances);
    }

    @GetMapping("/backTo")
    @ApiOperation("回退节点")
    public ResultBody backTo(String taskId, String targetTaskId, String eventId) {

        Task task = taskService.createTaskQuery().taskDefinitionKey(taskId).singleResult();
        List<HistoricActivityInstance> instances = (List<HistoricActivityInstance>) frontBack(eventId).getBody();
        HistoricActivityInstance target = null;
        for(HistoricActivityInstance instance : instances){
            if(targetTaskId.equals(instance.getActivityId())){
                target = instance;
                break;
            }
        }
        if(target == null){
            return Results.BAD__REQUEST.result("目标必选", null);
        }
        Process process = repositoryService.getBpmnModel(task.getProcessDefinitionId()).getMainProcess();
        //获取目标节点定义
        FlowNode targetNode = (FlowNode)process.getFlowElement(target.getActivityId());
        //删除当前运行任务
        String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(task.getId()));
        //流程执行到来源节点
        managementService.executeCommand(new SetFLowNodeAndGoCmd(targetNode, executionEntityId));
        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
    }

    @GetMapping("/giveTo")
    @ApiOperation("委托处理人处理")
    public ResultBody giveTo(String eventId, String userId, HttpServletRequest request){
        String token = request.getHeader(CommonConstants.X_ACCESS_TOKEN);
        String user = EncryptUtils.getIssuer(token);
        Task task = taskService.createTaskQuery().processInstanceId(eventId).taskAssignee(user).singleResult();
        taskService.setAssignee(task.getId(), userId);
        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
    }

    @GetMapping("/history")
    @ApiOperation("获取历史流程")
    public ResultBody history(int pageSize, int pageNum, HttpServletRequest request){

        String token = request.getHeader(CommonConstants.X_ACCESS_TOKEN);
        String user = EncryptUtils.getIssuer(token);
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().startedBy(user).listPage(pageNum, pageSize);
        List<HistoricInstanceVo> vos = new ArrayList(list.size());
        BeanUtils.copyProperties(list, vos);
        return Results.SUCCESS.result(CommonConstants.SUCCESS, vos);
    }
}
