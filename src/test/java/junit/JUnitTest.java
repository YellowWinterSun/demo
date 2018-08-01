package junit;

import com.dayi.demo.util.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * JUnit测试
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-resources-conf.xml","classpath:spring-mvc-conf.xml","classpath:spring-dao-conf.xml","classpath:spring-quartz.xml"})
public class JUnitTest {

    @Test
    public void test(){
        System.out.println("hi");
    }

    
    @Test
    public void getUUID(){
        System.out.println("==================== UUID ====================");
        for(int i = 0; i < 50; i++) {
            System.out.println("第" + (i+1) + "个为    :" + UUIDUtil.getUUID());
        }
        System.out.println("==================== UUID ====================");
    }
}
