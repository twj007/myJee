package com.jee.workflow.controller;

import com.common.constant.CommonConstants;
import com.common.model.workflow.vo.ProcessDefinitionVo;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/06
 **/
@RequestMapping("/sys/workflow/definition")
@RestController
@Slf4j
@Api("流程定义管理")
public class WorkflowDefinitionController extends BaseController{

    @PostMapping("/list")
    public ResultBody list(@RequestParam(name = "pageNum", required = false, defaultValue = "0")int pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10")int pageSize){
        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().latestVersion().listPage(pageNum, pageSize);
        List<ProcessDefinitionVo> definitionVos = new ArrayList<>();
        for(ProcessDefinition definition : definitions){
            ProcessDefinitionVo vo = new ProcessDefinitionVo();
            BeanUtils.copyProperties(definition, vo);
            definitionVos.add(vo);
        }
        return Results.SUCCESS.result(CommonConstants.SUCCESS, definitionVos);
    }

    @GetMapping("/status")
    @ApiOperation("修改流程定义状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "流程部署id"),
            @ApiImplicitParam(name = "status", value = "状态: suspend.挂起， running.恢复")
    })
    public ResultBody changeDefinitionStatus(String key, String status){
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).latestVersion().singleResult();
        if(definition == null){
            return Results.BAD__REQUEST.result("该流程不存在", null);
        }
        if(definition.isSuspended()){
            if(CommonConstants.SUSPEND.equals(status)){
                return Results.BAD__REQUEST.result("该流程已挂起,无需挂起", null);
            }else{
                repositoryService.activateProcessDefinitionById(definition.getId());
                return Results.SUCCESS.result("该流程已恢复", null);
            }
        }else{
            if(CommonConstants.RUNNING.equals(status)){
                return  Results.BAD__REQUEST.result("该流程运行中，无需恢复", null);
            }else{
                repositoryService.suspendProcessDefinitionById(definition.getId());
                return Results.SUCCESS.result("该流程已挂起", null);
            }
        }
    }

    @PostMapping("/deploy")
    @ApiOperation("部署流程实例")
    @ApiImplicitParam(name = "upload", value = "bpmn文件，zip文件")
    public ResultBody deploy(List<MultipartFile> uploads){
        int count = 0;
        List<String> failList = new ArrayList<>();
        if(uploads == null || uploads.size() == 0){
            return Results.BAD__REQUEST.result("上传文件必传", null);
        }
        for(MultipartFile upload : uploads){
            String filename = upload.getOriginalFilename();
            if(!filename.contains(CommonConstants.BPMNSUBFIX) && !filename.contains(CommonConstants.ZIPSUBFIX)){
                break;
            }
            if(filename.contains(CommonConstants.ZIPSUBFIX)){
                try {
                    Deployment deployment = repositoryService.createDeployment().addZipInputStream(new ZipInputStream(upload.getInputStream())).deploy();
                    ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).latestVersion().singleResult();
                    if(definition == null){
                        failList.add(upload.getOriginalFilename());
                        continue;
                    }
                    count ++;
                } catch (IOException e) {
                    failList.add(upload.getOriginalFilename());
                    log.error("[{}] [exception] error: {}", Thread.currentThread().getName(), e.fillInStackTrace());
                    e.printStackTrace();
                    continue;
                }
            }
            if(filename.contains(CommonConstants.BPMNSUBFIX)){
                try {

                    Deployment deployment = repositoryService.createDeployment().addInputStream(upload.getOriginalFilename(), upload.getInputStream()).deploy();
                    ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).latestVersion().singleResult();
                    if(definition == null){
                        failList.add(upload.getOriginalFilename());
                        continue;
                    }
                    count ++;
                } catch (IOException e) {
                    failList.add(upload.getOriginalFilename());
                    log.error("[{}] [exception] error: {}", Thread.currentThread().getName(), e.fillInStackTrace());
                    e.printStackTrace();
                    continue;
                }
            }

        }
        return Results.SUCCESS.result("成功部署: 共"+uploads.size()+"个"+count+"个流程", failList);
    }

    @RequestMapping("/definition")
    @ApiOperation("获取流程图,前端通过bpmn-js去解析绘制")
    public void showDefinition(String key, HttpServletResponse response){
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).latestVersion().singleResult();
        InputStream in = repositoryService.getResourceAsStream(definition.getDeploymentId(), definition.getResourceName() );
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭流
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @RequestMapping("/delete")
    @ApiOperation("删除流程定义")
    public ResultBody delete(String key){
        Deployment deployment = repositoryService.createDeploymentQuery().processDefinitionKey(key).orderByDeploymentId().desc().listPage(0, 1).get(0);
        repositoryService.deleteDeployment(deployment.getId());
        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
    }

    @RequestMapping("/instance/image")
    @ApiOperation("获取当前流程示意图和当前审批结点")
    public void task(String eventId, HttpServletResponse response){
        Task task = taskService.createTaskQuery().processInstanceId(eventId).singleResult();
        ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(eventId).singleResult();
        ProcessDefinition processDefinition;
        BpmnModel bpmnModel;
        if(instance != null){
            processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(instance.getProcessDefinitionId()).singleResult();
            bpmnModel = repositoryService.getBpmnModel(instance.getProcessDefinitionId());
        }else{
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(eventId).singleResult();
            processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(historicProcessInstance.getProcessDefinitionId()).singleResult();
            bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;

        // ID 为 流程定义Key
        Process process = bpmnModel.getProcessById(processDefinition.getKey());
        DefaultProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
        if(task != null){
            // 流程节点ID
            FlowElement flowElement = process.getFlowElement(task.getTaskDefinitionKey());
            List<String> highLightedActivities = new ArrayList<>();
            highLightedActivities.add(flowElement.getId());
            // 生成图片
            inputStream = generator.generateDiagram(bpmnModel, highLightedActivities, new ArrayList<>(), "宋体", "宋体", "宋体");
        }else{
            inputStream = generator.generateDiagram(bpmnModel, new ArrayList<>(), new ArrayList<>(), "宋体", "宋体", "宋体");
        }
        try {
            outputStream = response.getOutputStream();
            int size = IOUtils.copy(inputStream, outputStream);
            if(size == -1){
                log.error("[{}] [exception]: 超出缓存取大小，复制失败", Thread.currentThread().getName());
            }
            outputStream.flush();
        } catch (IOException e) {
            log.error("[{}] [exception]: cause [{}]", Thread.currentThread().getName(), e.fillInStackTrace());
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                log.error("[{}] [exception]: cause [{}]", Thread.currentThread().getName(), e.fillInStackTrace());
                e.printStackTrace();
            }

        }

    }

    @RequestMapping("/panel")
    @ApiOperation("流程图绘制页面")
    public ModelAndView panel(ModelAndView modelAndView){
        modelAndView.setViewName("bpmn");
        return modelAndView;
    }

}
