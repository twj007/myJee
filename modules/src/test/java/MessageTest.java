import com.component.RabbitProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/14
 **/
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = JeeApplication.class)
public class MessageTest {

    @Autowired
    private RabbitProducer producer;

    @Test
    public void testDeadMessage() throws InterruptedException {


            producer.send("[msg]");
            Thread.sleep(2000);

    }

    public static <R, S> boolean test1(Map<R, S> map){
        map.forEach((k, v)->{
            System.out.println(k);
            System.out.println(v);
        });
        return true;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "1");
        test1(map);
    }
}
