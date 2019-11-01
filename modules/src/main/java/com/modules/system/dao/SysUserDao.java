package com.modules.system.dao;

import com.common.model.vo.system.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.Set;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/30
 **/
@Mapper
public interface SysUserDao {


    @Select("select distinct r.role from sys_role r, sys_user u, sys_role_user ru where u.username=#{username, jdbcType=VARCHAR} and u.id = ru.uid and r.id = ru.rid")
    Set<String> getUserRolesSet(@Param("username") String username);

    @Select("select distinct p.permission from sys_permission p, sys_user u, sys_perm_user pu where u.username=#{username, jdbcType=VARCHAR} and p.id = pu.pid and u.id = pu.uid")
    Set<String> getUserPermissionSet(@Param("username") String username);

    @Select("select * from sys_user where username=#{username, jdbcType=VARCHAR}")
    @ResultType(SysUser.class)
    SysUser getUserByName(@Param("username") String username);
}
