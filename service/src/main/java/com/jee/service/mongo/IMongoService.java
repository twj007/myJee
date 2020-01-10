package com.jee.service.mongo;

import com.common.model.vo.MongoVo;

import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/06
 **/
public interface IMongoService {

    MongoVo getById(Long id);

    List<MongoVo> getByBrandId(Long brandId);

    long add(MongoVo mongoVo);

    long update(MongoVo mongoVo);

    long delete(MongoVo mongoVo);

}
