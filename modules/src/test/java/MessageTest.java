import com.JeeApplication;
import com.common.model.dto.announce.AnnounceDTO;
import com.common.model.dto.announce.AnnounceDetailDTO;
import com.component.RabbitProducer;
import com.jee.service.system.IAnnouncementService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/14
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JeeApplication.class)
public class MessageTest {

    @Autowired
    private RabbitProducer producer;

    @Test
    public void testDeadMessage() throws InterruptedException {


            producer.send("[msg]");
            Thread.sleep(2000);

    }

    @Autowired
    private IAnnouncementService announcementService;

    @Test
    public void testService(){
        AnnounceDTO announceDTO = new AnnounceDTO();
        announceDTO.setId(1L);
        AnnounceDTO announceDTO1 = announcementService.getAnnounceById(announceDTO);
        System.out.println(announceDTO1);
        Assert.assertNotEquals(null, announceDTO1);

    }

    @Test
    public void testAnnounceDetailList(){
        AnnounceDetailDTO announceDetailDTO = new AnnounceDetailDTO();
        announceDetailDTO.setMessageId(1L);
        List<AnnounceDetailDTO> announceDetailDTOList = announcementService.getAnnounceDetailList(announceDetailDTO);
        Assert.assertNotEquals(null, announceDetailDTOList);
        System.out.println(announceDetailDTOList);
    }

    @Test
    public void testUpdate(){
        AnnounceDTO announceDTO = new AnnounceDTO();
        announceDTO.setId(1L);
        announceDTO.setTitle("标题111111");
        long num = announcementService.updateAnnounce(announceDTO);
        Assert.assertNotEquals(0, num);
    }

    @Test
    public void testBatchInsert(){
        AnnounceDTO announceDTO = new AnnounceDTO();
        announceDTO.setTitle("yyyy");
        announceDTO.setStatus("Y");
        announceDTO.setAuthorId(3L);
        announceDTO.setContent("kkkkk");
        announceDTO.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        announceDTO.setSendType("U");
        announceDTO.setType("1");
        List<String> ids = new ArrayList<>();
        ids.add("1");ids.add("4");ids.add("3");
        announceDTO.setReceiverIds(ids);
        long num = announcementService.saveOrUpdate(announceDTO);
        Assert.assertNotEquals(0L, num);
    }


    public static <R, S> boolean test1(Map<R, S> map){
        map.forEach((k, v)->{
            System.out.println(k);
            System.out.println(v);
        });
        return true;
    }


    public static <T> boolean test2(List<T> list){
        list.forEach(r ->{
            System.out.println(r);
        });
        return true;
    }

  public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "1");
        test1(map);
    }
}
