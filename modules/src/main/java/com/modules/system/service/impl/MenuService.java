//package com.modules.system.service.impl;
//
//import com.common.model.dto.user.Menu;
//import com.modules.system.dao.MenuDao;
//import com.modules.system.service.IMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
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
//@Transactional(rollbackFor = Exception.class)
//public class MenuService implements IMenuService {
//
//
//    @Autowired
//    private MenuDao menuDao;
//
//    @Override
//    public int add(Menu menu) {
//        return menuDao.add(menu);
//    }
//
//    @Override
//    public int delete(Menu menu) {
//        return menuDao.delete(menu);
//    }
//
//    @Override
//    public int update(Menu menu) {
//        return menuDao.update(menu);
//    }
//
//    @Override
//    public List<Menu> find(Menu menu) {
//        return menuDao.find(menu);
//    }
//}
