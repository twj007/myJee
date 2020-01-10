import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/07
 **/
public class DateTest {

    public static void main(String[] args) {
        //2020-12-31
        System.out.println(DateTimeFormatter.ofPattern("YYYY-MM-dd").format(LocalDateTime.of(2019, 12, 31, 0, 0, 0)));
        //2019-12-31
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.of(2019, 12, 31, 0, 0, 0)));
    }
}
