package com.modules.system.dao;

import com.common.model.dto.user.UserInfo;
import com.common.model.vo.system.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/30
 **/
@Mapper
public interface SysUserDao {


    /***
     * 获取用户角色
     * @param username
     * @return
     */
    @Select("select distinct r.role from sys_role r, sys_user u, sys_role_user ru where u.username=#{username, jdbcType=VARCHAR} and u.id = ru.uid and r.id = ru.role_id")
    Set<String> getUserRolesSet(@Param("username") String username);

    /***
     * 获取用户权限
     * @param username
     * @return
     */
    @Select("select distinct p.permission from sys_permission p, sys_user u, sys_perm_user pu where u.username=#{username, jdbcType=VARCHAR} and p.id = pu.prem_id and u.id = pu.user_id")
    Set<String> getUserPermissionSet(@Param("username") String username);

    /***
     * 通过用户名获取用户
     * @param username
     * @return
     */
    @Select("select * from sys_user where username=#{username, jdbcType=VARCHAR}")
    @ResultType(SysUser.class)
    SysUser getUserByName(@Param("username") String username);

    /***
     * 通过角色获取用户
     * @param roleName
     * @return
     */
    @Select("select u.* from sys_user u, sys_role r, sys_role_user ru where u.id = ru.user_id and r.id = ru.role_id and r.role_name=#{roleName}")
    @ResultType(UserInfo.class)
    List<SysUser> findUserByRole(@Param("roleName") String roleName);

    /****
     * 检查用户名是否存在
     * @param username
     * @return
     */
    @Select("select count(*) from sys_user where username = #{username, jdbcType=VARCHAR}")
    @ResultType(Integer.class)
    int checkUserExists(@Param("username") String username);


    /***
     * 通过电话号码获取用户
     * @param phone
     * @return
     */
    @Select("select * from sys_user where phone = #{phone}")
    @ResultType(SysUser.class)
    SysUser getUserByPhone(@Param("phone") String phone);
}
