//package com.modules.mongodb.service;
//
//import com.common.model.vo.MongoVo;
//import com.jee.service.mongo.IMongoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2020/01/06
// **/
//@Service
//public class MongoService implements IMongoService {
//
//    @Autowired
//    private MongoTemplate template;
//
//    @Override
//    public MongoVo getById(Long id) {
//        return template.findById(id, MongoVo.class);
//    }
//
//    @Override
//    public List<MongoVo> getByBrandId(Long brandId) {
//        return template.find(new Query().addCriteria(Criteria.where("brandId").is(brandId)), MongoVo.class);
//    }
//
//    @Override
//    public long add(MongoVo mongoVo) {
//        template.insert(mongoVo);
//        return 1;
//    }
//
//    @Override
//    public long update(MongoVo mongoVo) {
//        Query query = new Query().addCriteria(Criteria.where("id").is(mongoVo.getId()));
//        Update update = new Update().set("brandName", mongoVo.getBrandName())
//                                    .set("price", mongoVo.getPrice())
//                                    .set("name", mongoVo.getName());
//        template.updateFirst(query, update, MongoVo.class);
//        return 1;
//    }
//
//    @Override
//    public long delete(MongoVo mongoVo) {
//        Query query = new Query().addCriteria(Criteria.where("id").is(mongoVo.getId()));
//        template.remove(query, MongoVo.class);
//        return 1;
//    }
//}
