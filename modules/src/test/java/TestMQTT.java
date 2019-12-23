import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/15
 **/
public class TestMQTT {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        // factory.setVirtualHost("");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        Connection conn = null;
        Channel channel = null;
        try {
            conn = factory.newConnection();
            channel = conn.createChannel();
            String sessionId = "51451";
            byte[] messageBodyBytes = "{'text':'Hello, world!中文'}".getBytes();
            // 这样就可以做到点对点通信了，amq.topic是默认exchange
            channel.basicPublish("amq.topic", sessionId, null, messageBodyBytes);
        }finally {
            if (channel != null) {
                channel.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
