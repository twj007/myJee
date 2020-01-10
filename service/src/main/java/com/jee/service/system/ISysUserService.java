package com.jee.service.system;

import com.common.model.vo.system.LoginUser;
import com.common.model.vo.system.SysUser;

import java.util.List;
import java.util.Set;

/***
 *
 */
public interface ISysUserService {
    /**
     * getUserRoleSet
     * @param username
     * @return
     */
    Set<String> getUserRolesSet(String username);

    /***
     * getUserPermissionSet
     * @param username
     * @return
     */
    Set<String> getUserPermissionsSet(String username);

    /***
     * getUserByName
     * @param username
     * @return
     */
    SysUser getUserByName(String username);

    /***
     * findUserByRole
     * @param taskId
     * @return
     */
    List<SysUser> findUserByRole(String taskId);

    LoginUser login(LoginUser user);

    int checkUserExists(String username);

    SysUser getUserByPhone(String phone);
}
