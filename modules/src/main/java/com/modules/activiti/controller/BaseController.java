package com.modules.activiti.controller;

import com.common.constant.CommonConstants;
import com.common.utils.EncryptUtils;
import org.activiti.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/06
 **/
@RestController
public class BaseController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    ManagementService managementService;



    public String getUserByRequest(HttpServletRequest request){
        String token = request.getHeader(CommonConstants.X_ACCESS_TOKEN);
        if(token == null){
            return null;
        }else{
            return EncryptUtils.getIssuer(token);
        }
    }
}
