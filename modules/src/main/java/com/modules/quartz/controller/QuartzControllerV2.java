package com.modules.quartz.controller;

import com.common.annotation.RepeatToken;
import com.common.model.dto.quartz.TaskError;
import com.common.model.dto.quartz.TaskInformation;
import com.common.model.dto.quartz.TaskRecord;
import com.common.model.vo.PageVo;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jee.service.quartz.QuartzServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/25
 **/
@RestController
@RequestMapping("/api/quartz/v2")
@Api("定时任务接口")
public class QuartzControllerV2 {

    @Autowired
    private QuartzServiceV2 quartzServiceV2;

    private static final String PREFIX = "/quartz/v2/";

    private static final String TASK = "task";
    private static final String RECORD = "record";
    private static final String ERROR = "error";

    @ApiOperation(value = "获取list页面,用于统一的页面跳转", notes = "list路径")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelAndView", readOnly = true),
            @ApiImplicitParam(name = "view")
    })
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ModelAndView viewPage(ModelAndView modelAndView,
                                 @PathVariable(name = "page")String page,
                                 @RequestParam(name = "id", required = false) Long id,
                                 @RequestParam(name = "taskNo", required = false)String taskNo){
        if(id != null){
            modelAndView.addObject("id", id);
        }
        if(!StringUtils.isEmpty(taskNo)){
            modelAndView.addObject("taskNo", taskNo);
        }
        modelAndView.setViewName(PREFIX + page);
        return modelAndView;
    }


    @RequestMapping(value = "/list/{type}",  method = RequestMethod.GET)
    @ApiOperation(value = "统一获取list数据", notes = "list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchStr"),
            @ApiImplicitParam(name = "pageNum", defaultValue = "0"),
            @ApiImplicitParam(name = "pageSize", defaultValue = "5")
    })
    public ResultBody listTask(TaskInformation information,
                               TaskRecord record,
                               TaskError error,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
                               @PathVariable(name = "type")String type) {

        Page page = PageHelper.startPage(pageNum, pageSize);
        PageVo pageVo = new PageVo();
        switch (type){
            case TASK:
                List<TaskInformation> tasks = quartzServiceV2.listTask(information);
                pageVo.setRows(tasks);
                break;
            case RECORD:
                List<TaskRecord> records = quartzServiceV2.listRecord(record);
                pageVo.setRows(records);
                break;
            case ERROR:
                List<TaskError> errors = quartzServiceV2.listError(error);
                pageVo.setRows(errors);
                break;
            default:
                return Results.BAD__REQUEST.result("访问路径不正确", null);
        }
        pageVo.setTotal(page.getTotal());
        return Results.SUCCESS.result("success", pageVo);
    }


    @RequestMapping(value = "/add/{view}", method = RequestMethod.POST)
    @ApiOperation("增加定时任务，其他的两个基本没用（内部获取添加）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "information", allowEmptyValue = true, paramType = "TaskInformation"),
            @ApiImplicitParam(name = "record", allowEmptyValue = true, paramType = "TaskRecord"),
            @ApiImplicitParam(name = "error", allowEmptyValue = true, paramType = "TaskError"),
            @ApiImplicitParam(name = "view", paramType = "String")
    })
    @RepeatToken
    public ResultBody add(TaskInformation information,
                          TaskRecord record,
                          TaskError error,
                          @PathVariable(name = "view")String view) throws SchedulerException {
        ResultBody result;
        switch (view){
            case TASK:
                result = quartzServiceV2.addTask(information);
                break;
            case RECORD:
                result = quartzServiceV2.addRecord(record);
                break;
            case ERROR:
                result = quartzServiceV2.addError(error);
                break;
            default:
                result = Results.BAD__REQUEST.result("访问路径不正确", null);
        }

        return result;
    }

    @RequestMapping(value = "/update/{view}", method = RequestMethod.POST)
    @ApiOperation("更新定时任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "information", allowEmptyValue = true, paramType = "TaskInformation"),
            @ApiImplicitParam(name = "record", allowEmptyValue = true, paramType = "TaskRecord"),
            @ApiImplicitParam(name = "error", allowEmptyValue = true, paramType = "TaskError"),
            @ApiImplicitParam(name = "view", paramType = "String")
    })
    @RepeatToken
    public ResultBody update(TaskInformation information,
                             TaskRecord record,
                             TaskError error,
                             @PathVariable(name = "view")String view){
        ResultBody result;
        switch (view){
            case TASK:
                int num = quartzServiceV2.updateTask(information);
                if(num > 0){
                    result = Results.SUCCESS.result("success", null);
                }else{
                    result = Results.BAD__REQUEST.result("保存失败", null);
                }
                break;
            case RECORD:
                result = quartzServiceV2.updateRecord(record);
                break;
            case ERROR:
                result = quartzServiceV2.updateError(error);
                break;
            default:
                result = Results.BAD__REQUEST.result("访问路径不正确", null);
        }

        return result;
    }

    @RequestMapping(value = "/task/updateAndReDeploy", method = RequestMethod.POST)
    @ApiOperation("更新并重新发布job")
    @ApiImplicitParam(name = "information", allowEmptyValue = true, paramType = "TaskInformation")
    @RepeatToken
    public ResultBody updateAndReDeploy(TaskInformation information) throws SchedulerException {
        if(information.getId() == null){
            return Results.BAD__REQUEST.result("参数必须", null);
        }
        return quartzServiceV2.updateAndReDeploy(information);
    }

    @RequestMapping(value = "/delete/{view}", method = RequestMethod.GET)
    @ApiOperation("删除job（这个接口暂时没用）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "information", allowEmptyValue = true, paramType = "TaskInformation"),
            @ApiImplicitParam(name = "record", allowEmptyValue = true, paramType = "TaskRecord"),
            @ApiImplicitParam(name = "error", allowEmptyValue = true, paramType = "TaskError"),
            @ApiImplicitParam(name = "view", paramType = "String")
    })
    @RepeatToken
    public ResultBody delete(TaskInformation information,
                             TaskRecord record,
                             TaskError error,
                             @PathVariable(name = "view")String view){
        switch (view){
            case TASK:
                quartzServiceV2.deleteTask(information);
                break;
            default:
                return Results.BAD__REQUEST.result("访问路径不正确", null);
        }

        return Results.SUCCESS.result("success", null);
    }

    @RequestMapping(value = "/runRightNow", method = RequestMethod.GET)
    @ApiOperation("立即运行job")
    @ApiImplicitParam(name = "information", allowEmptyValue = true, paramType = "TaskInformation")
    public ResultBody runRightNow(TaskInformation information){
        if(StringUtils.isEmpty(information.getTaskno()) && information.getId() == null){
            return Results.BAD__REQUEST.result("参数必须", null);
        }
        return quartzServiceV2.runRightNow(information);

    }

    @RequestMapping(value = "/changeStatus", method = RequestMethod.GET)
    @ApiOperation("变更job状态")
    @ApiImplicitParam(name = "information", allowEmptyValue = true, paramType = "TaskInformation")
    @RepeatToken
    public ResultBody changeStatus(TaskInformation information) throws SchedulerException {
        return quartzServiceV2.changeStatus(information);
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResultBody dosomething(HttpServletRequest request, HttpServletResponse response){

        return null;
    }









}
