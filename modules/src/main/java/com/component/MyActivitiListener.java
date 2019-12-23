package com.component;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/20
 **/
public class MyActivitiListener implements TaskListener {

    private static final Logger logger = LoggerFactory.getLogger(MyActivitiListener.class);

    @Override
    public void notify(DelegateTask delegateTask) {
        String assign = delegateTask.getAssignee();
        logger.info("[workflow]： start user is: {}", assign);
    }
}
