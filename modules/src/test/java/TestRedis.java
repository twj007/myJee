import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.Objects;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/18
 **/
public class TestRedis {

    private static final Logger logger = LoggerFactory.getLogger(TestRedis.class);

    /***
     * 生成8999 个随机数 1000 ~ 9999
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        long x = 1000;
        logger.info("[jedis] generate verify_code");
        while(x < 9999){
            jedis.sadd("verify_code", String.valueOf(x));
            x++;
        }
        logger.info("[jedis] get a random code: {}", jedis.srandmember("verify_code"));
        Transaction transaction = jedis.multi();
        logger.info("[jedis] start transaction");
        transaction.del("verify_code");
//        logger.info("[jedis] rollback");
//        transaction.discard();
//        String random = jedis.srandmember("verify_code");
//        logger.info("[jedis] now the random key is {}", random);
        transaction.exec();
        
        logger.info("[jedis] execute transaction now: {}", jedis.srandmember("verify_code"));
        logger.info("[jedis] close connection");
        jedis.disconnect();

//        Entity e1 = new Entity("111", "222");
//        Entity e2 = new Entity("111", "222");
//        System.out.println(e1 == e2);
//        System.out.println(e1.equals(e2));
//        System.out.println(e1.hashCode());
//        System.out.println(e2.hashCode());
//        HashMap m = new HashMap();
//        m.put(e1, "111");
//        m.put(e2, "222");
//        System.out.println(m);



    }
}
class Entity{

    public Entity(){}
    public Entity(String name, String value){
        this.name = name;
        this.value = value;
    }

    private String name;

    private String value;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Entity entity = (Entity) o;
//        return Objects.equals(name, entity.name) &&
//                Objects.equals(value, entity.value);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
