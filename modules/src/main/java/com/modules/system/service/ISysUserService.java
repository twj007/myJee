package com.modules.system.service;

import com.common.model.vo.system.SysUser;

import java.util.Set;

public interface ISysUserService {
    Set<String> getUserRolesSet(String username);

    Set<String> getUserPermissionsSet(String username);

    SysUser getUserByName(String username);
}
