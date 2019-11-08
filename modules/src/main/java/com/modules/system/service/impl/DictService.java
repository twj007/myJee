//package com.modules.system.service.impl;
//
//import com.common.model.dto.dict.DictDetail;
//import com.common.model.vo.system.SysDict;
//import com.modules.system.dao.DictDao;
//import com.modules.system.service.IDictService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2019/11/07
// **/
//@Service
//public class DictService implements IDictService {
//
//    @Autowired
//    private DictDao dictDao;
//
//    @Override
//    public List<SysDict> list(SysDict dict) {
//        return dictDao.list(dictDao);
//    }
//
//    @Override
//    public DictDetail findOne(SysDict dict) {
//        return dictDao.findOne(dict);
//    }
//
//    @Override
//    public int delete(SysDict dict) {
//        return dictDao.delete(dict);
//    }
//
//    @Override
//    public int update(SysDict dict) {
//        return dictDao.update(dict);
//    }
//
//    @Override
//    public int add(SysDict dict) {
//        return dictDao.add(dict);
//    }
//}
