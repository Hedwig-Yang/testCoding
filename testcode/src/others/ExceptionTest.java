package others;

import org.junit.Test;

/**
 * @Author:KUN
 * @Data:2021/4/26 14:53
 * @Description: try-catch后正常运行
 * @Version:1.0
 */
public class ExceptionTest {

    @Test
    public void test1(){
        try {
            int i = 5/0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("cuo");
    }


}
