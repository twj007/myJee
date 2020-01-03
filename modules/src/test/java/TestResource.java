import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Arrays;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/02
 **/
public class TestResource {

    public static void main(String[] args) throws IOException {
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources( "classpath*:templates/**");
        Arrays.stream(resources).forEach(r ->
        {
            if(r.isFile() && r.getFilename().indexOf(".html") != -1){
                System.out.println(r.getFilename());
            }
        }
        );
    }
}
