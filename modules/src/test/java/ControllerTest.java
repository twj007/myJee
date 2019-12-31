import com.JeeApplication;
import com.modules.quartz.service.QuartzServiceV2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    QuartzServiceV2 quartzService;

    @Test
    public void testAdd(){

    }

    @Test
    public void testUpdate(){

    }

    @Test
    public void testDelete(){

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
