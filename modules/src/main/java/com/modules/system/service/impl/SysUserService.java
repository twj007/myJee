package com.modules.system.service.impl;

import com.common.model.vo.system.SysUser;
import com.modules.system.dao.SysUserDao;
import com.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/30
 **/
@Service
@Transactional
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
}
