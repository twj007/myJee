package com.modules.system.controller;

import com.common.model.vo.PageVo;
import com.common.model.vo.system.SysUser;
import com.common.utils.ResultBody;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/31
 **/
@RestController
@RequestMapping("/sys/user")
@Api("用户管理接口")
public class SysUserController {

    @RequestMapping("/exportExcel")
    @ApiOperation("导出名单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型"),
            @ApiImplicitParam(name = "ids", value = "导出id")
    })
    public void exportExcel(String type, List<Long> ids){}

    @RequestMapping("/importUser")
    @ApiOperation("通过excel增量导入名单")
    @ApiImplicitParam(name = "users", value = "新增人员")
    public ResultBody importUser(MultipartFile users){return null;}

    @RequestMapping("/refreshUser")
    @ApiOperation("更新名单人员信息")
    public ResultBody refreshUser(MultipartFile users){return null;}

    @RequestMapping("/list")
    @ApiOperation("分页显示人员")
    @ApiImplicitParam(name = "pageVo", value = "分页实体类")
    public ResultBody list(PageVo pageVo){return null;}

    @RequestMapping("/findOne")
    @ApiOperation("获取某个人的详情信息")
    @ApiImplicitParam(name = "user", value = "用户信息")
    public ResultBody findOne(SysUser user){return null;}

    @RequestMapping("/add")
    @ApiOperation("单个添加人员")
    @ApiImplicitParam(name = "sysUser", value = "用户信息")
    public ResultBody add(SysUser sysUser){return null;}

    @RequestMapping("/disable")
    @ApiOperation("封禁/恢复人员状态")
    @ApiImplicitParam(name = "id", value = "用户id")
    public ResultBody disableUser(long id){return null;}

    @RequestMapping("/disable/batch")
    @ApiOperation("批量封禁/恢复人员状态")
    @ApiImplicitParam(name = "ids", value = "用户id")
    public ResultBody disableBatch(List<Long> ids){return null;}

    @RequestMapping("modify")
    @ApiOperation("修改人员信息")
    @ApiImplicitParam(name = "sysUser", value = "用户")
    public ResultBody modifyUser(SysUser sysUser){return null;}

}

