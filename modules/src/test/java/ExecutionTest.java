import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/25
 **/
public class ExecutionTest {

    public static void main(String[] args) throws Exception{
        String[] command = new String[]{"node", "-v"};
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String r = null;
        while((r = reader.readLine()) != null){
            System.out.println(r);
        }
        System.out.println(process.waitFor());

    }
}
