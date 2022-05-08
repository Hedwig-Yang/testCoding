package others;

import org.junit.Test;

/**
 * @Author:Z
 * @Date:2021/11/29 18:40
 * @Description: String相关测试
 * @Version:1.0
 */
public class StringTest {

    @Test
    public void testContain(){
        String s = null;
        System.out.println(s.contains("abc"));  //java.lang.NullPointerException
    }
}
