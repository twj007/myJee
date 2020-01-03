package com.jee.service.system;

import com.common.model.dto.announce.AnnounceDTO;
import com.common.model.dto.announce.AnnounceDetailDTO;

import java.util.List;

/***
 * @author info
 */
public interface IAnnouncementService {


    long sendUnRead(String param);

    long saveAndSend(AnnounceDTO announce);

    long send(List<Long> ids);

    long saveOrUpdate(AnnounceDTO announce);

    long deleteAnnounceByIds(List<Long> ids);

    long updateAnnounce(AnnounceDTO announce);

    AnnounceDTO getAnnounceById(AnnounceDTO a);

    List<AnnounceDTO> getAnnounceListByUser(AnnounceDTO announce);

    List<AnnounceDetailDTO> getAnnounceDetailList(AnnounceDetailDTO announce);

    List<AnnounceDTO> getAnnounceList(AnnounceDTO announce);

    boolean broadcast(AnnounceDTO announceDTO);

    boolean sendToListUser(AnnounceDTO announceDTO);
}
