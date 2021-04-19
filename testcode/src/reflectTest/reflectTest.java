package reflectTest;

import org.junit.Test;

/**
 * @Author:KUN
 * @Data:2021/4/19 16:20
 * @Description: TODO
 * @Version:1.0
 */
public class reflectTest {

    @Test
    public void test1(){
        try {
            System.out.println(Class.forName("reflectTest.GetMethodClass").getMethod("method1", String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
