package com.modules.system.controller;

import com.common.model.vo.system.SysRole;
import com.common.utils.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/31
 **/
@RestController
@RequestMapping("/sys/role")
@Api("角色管理接口")
public class SysRoleController {

    @RequestMapping("/list")
    @ApiOperation("分页查询角色")
    @ApiImplicitParam(name = "role", value = "查询条件")
    public ResultBody list(SysRole role){return null;}

    @RequestMapping("/findOne")
    @ApiOperation("查询角色详情")
    @ApiImplicitParam(name = "role", value = "查询条件")
    public ResultBody findOne(SysRole role){return null;}

    @RequestMapping("/add")
    @ApiOperation("增加角色")
    @ApiImplicitParam(name = "role", value = "角色条件")
    public ResultBody add(SysRole role){return null;}

    @RequestMapping("/delete")
    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "role", value = "角色条件")
    public ResultBody deleteRole(SysRole role){return null;}

    @RequestMapping("/disable")
    @ApiOperation("禁用角色角色")
    @ApiImplicitParam(name = "role", value = "角色条件")
    public ResultBody disableRole(SysRole role){return null;}

    @RequestMapping("/update")
    @ApiOperation("更新角色信息")
    @ApiImplicitParam(name = "role", value = "角色条件")
    public ResultBody updateRole(SysRole role){return null;}
}
