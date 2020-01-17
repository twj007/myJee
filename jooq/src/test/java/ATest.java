import java.sql.Timestamp;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/17
 **/
public class ATest {
    public static void main(String[] args) {
        String time = "2019-10-16 18:05:22";
        Timestamp timestamp = Timestamp.valueOf(time);
        System.out.println(timestamp.getTime());
    }
}
