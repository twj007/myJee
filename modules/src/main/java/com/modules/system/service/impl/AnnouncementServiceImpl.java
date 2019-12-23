//package com.modules.system.service.impl;
//
//import com.common.model.vo.system.AnnouncementVo;
//import com.modules.system.dao.AnnouncementDao;
//import com.modules.system.service.IAnnouncementService;
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
// **@Date: 2019/11/15
// **/
//@Service
//public class AnnouncementServiceImpl implements IAnnouncementService {
//
//    private static final String SAVED = "3";
//
//    @Autowired
//    private AnnouncementDao announcementDao;
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public long saveAnnouncement(AnnouncementVo announcementVo) {
//        return announcementDao.saveAnnouncement(announcementVo);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public long addAnnouncement(AnnouncementVo announcementVo) {
//        if(SAVED.equals(announcementVo.getStatus())){
//            return updateAnnouncement(announcementVo);
//        }else{
//            return saveAnnouncement(announcementVo);
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public long updateAnnouncement(AnnouncementVo announcementVo) {
//        return announcementDao.updateAnnouncement(announcementVo);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<AnnouncementVo> list(AnnouncementVo announcementVo) {
//        return announcementDao.list(announcementVo);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public AnnouncementVo getDetail(AnnouncementVo announcementVo) {
//        return announcementDao.getDetail(announcementVo);
//    }
//
//
//}
