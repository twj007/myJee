//package com.modules.system.controller;
//
//import com.common.constant.CommonConstants;
//import com.common.model.dto.user.Menu;
//import com.common.model.vo.PageVo;
//import com.common.utils.ResultBody;
//import com.common.utils.Results;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.modules.system.service.IMenuService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2019/11/07
// **/
//@RestController
//@RequestMapping("/sys/menu")
//@Api("菜单管理")
//public class MenuController {
//
//    @Autowired
//    private IMenuService menuService;
//
//    @GetMapping("/add")
//    @ApiOperation("增加菜单")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "menu", value = "菜单vo")
//    })
//    public ResultBody add(Menu menu){
//        int nums = menuService.add(menu);
//        if(nums == 1){
//            return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
//        }else{
//            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
//        }
//    }
//
//
//    @GetMapping("/delete")
//    @ApiOperation("删除")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "menu", value = "菜单vo")
//    })
//    public ResultBody del(Menu menu){
//        int nums = menuService.delete(menu);
//        if(nums == 1){
//            return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
//        }else{
//            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
//        }
//    }
//
//
//    @GetMapping("/update")
//    @ApiOperation("更新")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "menu", value = "菜单vo")
//    })
//    public ResultBody update(Menu menu){
//        int nums = menuService.update(menu);
//        if(nums == 1){
//            return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
//        }else{
//            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
//        }
//    }
//
//
//    @GetMapping("/find")
//    @ApiOperation("查询")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "menu", value = "菜单vo"),
//            @ApiImplicitParam(name = "pageNum", value = "起始"),
//            @ApiImplicitParam(name = "pageSize", value = "大小")
//    })
//    public ResultBody find(Menu menu, @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "1") Integer pageSize){
//
//        Page page = PageHelper.startPage(pageNum, pageSize);
//
//        List<Menu> menus = menuService.find(menu);
//        PageVo vo = new PageVo();
//        vo.setData(menus);
//        vo.setSize(page.getTotal());
//        return Results.SUCCESS.result(CommonConstants.SUCCESS, vo);
//    }
//
//}
