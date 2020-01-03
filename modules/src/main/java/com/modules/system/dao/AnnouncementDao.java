package com.modules.system.dao;

import com.common.model.dto.announce.AnnounceDTO;
import com.common.model.dto.announce.AnnounceDetailDTO;
import org.mapstruct.Mapper;

import java.util.List;

/***
 * @author info
 * announce dao
 */
@Mapper
public interface AnnouncementDao {

    List<AnnounceDTO> getAnnounceList(AnnounceDTO announce);

    List<AnnounceDetailDTO> getAnnounceDetailList(AnnounceDetailDTO announceDetailDTO);

    AnnounceDTO getAnnounceById(AnnounceDTO a);

    long updateAnnounceById(AnnounceDTO announce);

    long deleteAnnounceByIds(List<Long> ids);

    int getAnnounceCount(AnnounceDTO announce);

    List<AnnounceDTO> getAnnounceListByIds(List<Long> ids);

    long saveAnnounce(AnnounceDTO announce);

    long saveAnnounceDetails(List<AnnounceDetailDTO> details);

    List<AnnounceDTO> getUnReadAnnounce();
}
