package com.modules.system.service.impl;

import com.common.annotation.DataSource;
import com.common.model.vo.system.LoginUser;
import com.common.model.vo.system.SysUser;
import com.modules.system.dao.SysUserDao;
import com.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/30
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@DataSource
public class SysUserService implements ISysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public Set<String> getUserRolesSet(String username) {
        return sysUserDao.getUserRolesSet(username);
    }

    @Override
    public Set<String> getUserPermissionsSet(String username) {
        return sysUserDao.getUserPermissionSet(username);
    }

    @Override
    public SysUser getUserByName(String username) {
        return sysUserDao.getUserByName(username);
    }

    @Override
    public List<SysUser> findUserByRole(String taskId) {
        return sysUserDao.findUserByRole(taskId);
    }

    @Override
    public LoginUser login(LoginUser user) {
        return null;
    }
}
