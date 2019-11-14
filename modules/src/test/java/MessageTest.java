import com.JeeApplication;
import com.component.RabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
