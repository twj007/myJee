//package com.modules.mongodb.controller;
//
//import com.common.model.vo.MongoVo;
//import com.common.utils.ResultBody;
//import com.common.utils.Results;
//import com.jee.service.mongo.IMongoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2020/01/06
// **/
//@RestController
//@RequestMapping("/api/v2/mongo")
//public class MongoController {
//
//    @Autowired
//    private IMongoService mongoService;
//
//    @RequestMapping("/findById")
//    public ResultBody findById(Long id){
//        MongoVo vo = mongoService.getById(id);
//        return Results.SUCCESS.result("", vo);
//    }
//
//    @RequestMapping("/findByBrandId")
//    public ResultBody findByBrandId(Long brandId){
//        List<MongoVo> res = mongoService.getByBrandId(brandId);
//        return Results.SUCCESS.result("", res);
//    }
//
//    @RequestMapping("/add")
//    public ResultBody add(MongoVo mongoVo){
//        mongoService.add(mongoVo);
//        return Results.SUCCESS.result("", null);
//    }
//
//    @RequestMapping("/update")
//    public ResultBody update(MongoVo mongoVo){
//        mongoService.update(mongoVo);
//        return Results.SUCCESS.result("", null);
//    }
//
//    @RequestMapping("/delete")
//    public ResultBody delete(MongoVo mongoVo){
//        mongoService.delete(mongoVo);
//        return Results.SUCCESS.result("", null);
//    }
//}
