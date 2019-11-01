package com.modules.system.controller;

import com.common.model.vo.system.SysDict;
import com.common.utils.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/01
 **/
@RestController
@RequestMapping("/sys/dict")
@Api("字典管理接口")
public class DictController {

    @RequestMapping("/list")
    @ApiOperation("分页查询字典")
    @ApiImplicitParam(name = "dict", value = "字典")
    public ResultBody list(SysDict dict){return null;}

    @RequestMapping("/findOne")
    @ApiOperation("查询字典明细")
    @ApiImplicitParam(name = "dict", value = "字典")
    public ResultBody findOne(SysDict dict){return null;}

    @RequestMapping("/delete")
    @ApiOperation("删除字典")
    @ApiImplicitParam(name = "dict", value = "字典")
    public ResultBody delete(SysDict dict){return null;}

    @RequestMapping("/update")
    @ApiOperation("更新字典")
    @ApiImplicitParam(name = "dict", value = "字典")
    public ResultBody update(SysDict dict){return null;}

    @RequestMapping("/add")
    @ApiOperation("添加字典")
    @ApiImplicitParam(name = "dict", value = "字典")
    public ResultBody add(SysDict dict){return null;}

}
