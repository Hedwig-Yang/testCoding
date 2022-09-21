package others;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author:KUN
 * @Data:2021/4/26 14:53
 * @Description: try-catch后正常运行
 * @Version:1.0
 */
@Slf4j
public class ExceptionTest {

    @Test
    public void test1(){
        try {
            //int i = 5/0;
            testThrow();
        } catch (Exception e) {
            //log.error("/ by zero");
            log.error("捕捉到异常");
            //e.printStackTrace();
        }
        System.out.println("cuo");
    }

    public void testThrow() throws ArithmeticException{
        throw new ArithmeticException("发生除法分母为0异常");
    }

}
