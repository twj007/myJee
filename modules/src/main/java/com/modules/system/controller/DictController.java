//package com.modules.system.controller;
//
//import com.common.constant.CommonConstants;
//import com.common.model.dto.dict.DictDetail;
//import com.common.model.vo.PageVo;
//import com.common.model.vo.system.SysDict;
//import com.common.utils.ResultBody;
//import com.common.utils.Results;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.modules.system.service.IDictService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2019/11/01
// **/
//@RestController
//@RequestMapping("/sys/dict")
//@Api("字典管理接口")
//public class DictController {
//
//    @Autowired
//    private IDictService dictService;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @RequestMapping("/list")
//    @ApiOperation("分页查询字典")
//    @ApiImplicitParam(name = "dict", value = "字典")
//    public ResultBody list(SysDict dict, @RequestParam(name = "pageNum", required = false, defaultValue = "0")int pageNum, @RequestParam(name = "pageSize", required = false, defaultValue = "1")int pageSize){
//        Page page = PageHelper.startPage(pageNum, pageSize);
//        List<SysDict> dicts = dictService.list(dict);
//        PageVo vo = new PageVo();
//        vo.setData(dicts);
//        vo.setSize(page.getTotal());
//        return Results.SUCCESS.result(CommonConstants.SUCCESS, vo);
//    }
//
//    @RequestMapping("/findOne")
//    @ApiOperation("查询字典明细")
//    @ApiImplicitParam(name = "dict", value = "字典")
//    public ResultBody findOne(SysDict dict){
//        DictDetail detail = (DictDetail) redisTemplate.opsForValue().get(dict.getKeyType());
//        if(detail == null){
//            detail = dictService.findOne(dict);
//            redisTemplate.opsForValue().set(detail.getKeyType(), detail, 24, TimeUnit.HOURS);
//        }
//        return Results.SUCCESS.result(CommonConstants.SUCCESS, detail);
//    }
//
//    @RequestMapping("/delete")
//    @ApiOperation("删除字典")
//    @ApiImplicitParam(name = "dict", value = "字典")
//    public ResultBody delete(SysDict dict){
//        int num = dictService.delete(dict);
//        if(num == 0){
//            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
//        }
//        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
//    }
//
//    @RequestMapping("/update")
//    @ApiOperation("更新字典")
//    @ApiImplicitParam(name = "dict", value = "字典")
//    public ResultBody update(SysDict dict){
//        int num = dictService.update(dict);
//        if(num == 0){
//            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
//        }
//        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
//    }
//
//    @RequestMapping("/add")
//    @ApiOperation("添加字典")
//    @ApiImplicitParam(name = "dict", value = "字典")
//    public ResultBody add(SysDict dict){
//        int num = dictService.add(dict);
//        if(num == 0){
//            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
//        }
//        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
//    }
//
//}
