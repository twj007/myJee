package com.component;

import com.modules.system.service.ISysUserService;
import org.activiti.runtime.api.identity.UserGroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/05
 * 用于覆盖activiti中的ActivityUserGroupManagerImpl,
 * 因为里面使用了spring security的方式去获取组。
 **/
@Component
@Primary
public class NonSecurityGroupManager implements UserGroupManager {

    @Autowired
    @Lazy
    private ISysUserService sysUserService;

    @Override
    public List<String> getUserGroups(String s) {
        return null;
    }

    @Override
    public List<String> getUserRoles(String s) {

        List<String> roles = new ArrayList<>();
        roles.addAll(sysUserService.getUserRolesSet(s));
        return roles;
    }
}
