package com.modules.webservice.impl;

import com.common.model.vo.system.SysUser;
import com.jee.service.webservice.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/02
 **/
@Service
public class WebSysUserService implements ISysUserService {

    @Autowired
    private com.jee.service.system.ISysUserService userService;

    @Override
    public List<SysUser> findUserByRole(String taskId) {
        return userService.findUserByRole(taskId);
    }
}
