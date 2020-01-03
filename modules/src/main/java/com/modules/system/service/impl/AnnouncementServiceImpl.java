package com.modules.system.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.common.model.dto.announce.AnnounceDTO;
import com.common.model.dto.announce.AnnounceDetailDTO;
import com.jee.service.system.IAnnouncementService;
import com.modules.system.dao.AnnouncementDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/15
 **/
@SuppressWarnings("Duplicates")
@Service
@Slf4j
public class AnnouncementServiceImpl implements IAnnouncementService {

    private static final String BROADCAST = "B";

    private static final String TORECEIVER = "U";

    @Autowired
    private AnnouncementDao announcementDao;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @Override
    public long sendUnRead(String param) {
        List<AnnounceDTO> announceDTOList = announcementDao.getUnReadAnnounce();
        for(AnnounceDTO announce : announceDTOList){
            if(BROADCAST.equals(announce.getSendType())){
                if(broadcast(announce)){
                    continue;
                }else{
                    return 0;
                }
            } else if(TORECEIVER.equals(announce.getSendType())){
                if(sendToListUser(announce)){
                    continue;
                }else{
                    return 0;
                }
            } else{
                return 0;
            }
        }
        return 1;
    }

    @Override
    public long saveAndSend(AnnounceDTO announce) {
        if(BROADCAST.equals(announce.getSendType())){
            if(broadcast(announce)){
                return announcementDao.saveAnnounce(announce);
            }

        }
        if(TORECEIVER.equals(announce.getSendType())){
            if(sendToListUser(announce)){
                if(announcementDao.saveAnnounce(announce) > 0L){
                    List<String> ids = announce.getReceiverIds();
                    List<AnnounceDetailDTO> details = new ArrayList<>();
                    ids.forEach(id -> {
                        AnnounceDetailDTO detail = new AnnounceDetailDTO();
                        detail.setMessageId(announce.getId());
                        detail.setSendDate(announce.getSendDate());
                        detail.setSendStatus("Y");
                        details.add(detail);
                    });
                    return announcementDao.saveAnnounceDetails(details);
                }


            }
        }

        return 0;
    }

    @Override
    public long send(List<Long> ids) {
        AtomicInteger num = new AtomicInteger();
        List<AnnounceDTO> announceDTOList = announcementDao.getAnnounceListByIds(ids);
        announceDTOList.forEach(announceDTO -> {
            if(BROADCAST.equals(announceDTO.getType())){
                if(broadcast(announceDTO)){
                    updateAnnounce(announceDTO);
                    num.getAndIncrement();
                }

            }else{
                if(sendToListUser(announceDTO)){
                    updateAnnounce(announceDTO);
                    num.getAndIncrement();
                }

            }
        });
        return num.longValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public long saveOrUpdate(AnnounceDTO announce) {
        int count = announcementDao.getAnnounceCount(announce);
        if(count > 0){
            return announcementDao.updateAnnounceById(announce);
        }else{
            long num = announcementDao.saveAnnounce(announce);
            if(BROADCAST.equals(announce.getType())){
                return num;
            }
            if(num > 0){
                List<String> ids = announce.getReceiverIds();
                List<AnnounceDetailDTO> details = new ArrayList<>();
                if(ids == null){
                    throw new RuntimeException("必须选择接收人");
                }
                ids.forEach(id -> {
                    AnnounceDetailDTO detail = new AnnounceDetailDTO();
                    detail.setMessageId(announce.getId());
                    detail.setUserId(Long.valueOf(id));
                    detail.setSendDate(announce.getSendDate());
                    detail.setSendStatus("N");
                    details.add(detail);
                });
                return announcementDao.saveAnnounceDetails(details);
            }else{
                throw new RuntimeException("保存公告时异常，回滚事务");
            }

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public long deleteAnnounceByIds(List<Long> ids) {
        return announcementDao.deleteAnnounceByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public long updateAnnounce(AnnounceDTO announce) {
        return announcementDao.updateAnnounceById(announce);
    }

    @Override
    public AnnounceDTO getAnnounceById(AnnounceDTO a) {
        return announcementDao.getAnnounceById(a);
    }

    @Override
    public List<AnnounceDTO> getAnnounceListByUser(AnnounceDTO announce) {
        return announcementDao.getAnnounceList(announce);
    }

    @Override
    public List<AnnounceDetailDTO> getAnnounceDetailList(AnnounceDetailDTO announce) {
        return announcementDao.getAnnounceDetailList(announce);
    }

    @Override
    public List<AnnounceDTO> getAnnounceList(AnnounceDTO announce) {
        return announcementDao.getAnnounceList(announce);
    }

    @Override
    public boolean broadcast(AnnounceDTO announceDTO) {
        boolean result = false;
        try{
            log.info("[web socket] send broadcast info: {}", announceDTO);
            messagingTemplate.convertAndSend("/topic", JSONUtils.toJSONString(announceDTO));
            result = true;
        }catch (Exception e){
            log.error("[web socket] error happened when send broadcast message : {}", e.fillInStackTrace());

        }
        return result;
    }

    @Override
    public boolean sendToListUser(AnnounceDTO announceDTO) {
        boolean result = false;
        try{
            List<String> ids = announceDTO.getReceiverIds();
            ids.forEach(s ->{
                log.info("[web socket] send broadcast info: {}", announceDTO);
                messagingTemplate.convertAndSendToUser(s, "/queue/getResponse", JSONUtils.toJSONString(announceDTO));
            });
            result = true;
        }catch (Exception e){
            log.error("[web socket] error happened when send one-to-one message : {}", e.fillInStackTrace());
        }
        return result;
    }
}
