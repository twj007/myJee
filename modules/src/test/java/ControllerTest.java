import com.JeeApplication;
import com.common.model.ActivityModel;
import com.common.model.ActivityModelDetail;
import com.modules.system.dao.TestDao;
import com.modules.system.service.impl.TestService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/25
 **/
@RunWith(SpringJUnit4ClassRunner.class)
//@AutoConfigureMockMvc
@SpringBootTest(classes = JeeApplication.class)
public class ControllerTest {


//    MockMvc mockMvc;

    @Autowired
    private TestDao testDao;

    @Test
    public void testQuery(){
        List<ActivityModel> models = testDao.getActivities();
        models.forEach(m -> {
            System.out.println(m);
        });
        Assert.assertNotEquals(null, models);
    }

    @Test
    public void testInsert(){
        List<ActivityModelDetail> details = new ArrayList<>();
        ActivityModelDetail d1 = new ActivityModelDetail();
        d1.setPlanId(1L);
        d1.setMoney(new BigDecimal(20));
        d1.setMonth(1);
        d1.setFromDay(1);
        d1.setToDay(20);
        d1.setYear(2019);
        ActivityModelDetail d2 = new ActivityModelDetail();
        d2.setPlanId(1L);
        d2.setMoney(new BigDecimal(25));
        d2.setMonth(2);
        d2.setFromDay(1);
        d2.setToDay(28);
        d2.setYear(2019);
        ActivityModelDetail d3 = new ActivityModelDetail();
        d3.setPlanId(1L);
        d3.setMoney(new BigDecimal(20));
        d3.setMonth(1);
        d3.setFromDay(1);
        d3.setToDay(15);
        d3.setYear(2019);
        details.add(d1); details.add(d2); details.add(d3);
        Long num = testDao.saveToTempTable(details);
        System.out.println(num);
        Assert.assertNotEquals(null, num);
    }


    @Test
    public void testDelete(){
        Long num = testDao.deleteTempDate();
        System.out.println(num);
        Assert.assertNotEquals(null, num);
    }

    @Test
    public void testGetDetail(){
        ActivityModelDetail d = new ActivityModelDetail();
        d.setPlanId(4L);
        List<ActivityModelDetail> details = testDao.getActivityDetails(d);
        details.forEach(v -> {
            System.out.println(v);
        });
        Assert.assertNotEquals(null, details);
    }

    @Test
    public void testInsertModel(){
        ActivityModel model = new ActivityModel();
        model.setStartDate(Date.from(LocalDateTime.of(2018, 7, 1, 0,0, 0).toInstant(ZoneOffset.UTC)));
        model.setEndDate(Date.from(LocalDateTime.of(2020, 3, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC)));
        model.setTotalMoney(new BigDecimal(200000));
        Long num = testService.addActivity(model);
        Assert.assertNotEquals(null, num);
    }


    @Autowired
    private TestService testService;

    @Test
    public void TestCutMoney(){
        testService.cutMoney();
    }


    @Before
    public void before(){

    }

    @After
    public void after(){

    }


//    @Test
//    public void testList() throws Exception {
//
//        String result = mockMvc.perform(
//                MockMvcRequestBuilders
//                        .get(URI.create("/quartz/list?pageNum=0&pageSize=5"))
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn().getResponse().getContentAsString();
//        System.out.println(result);
//
//    }
}
