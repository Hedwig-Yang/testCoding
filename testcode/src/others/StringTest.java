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

    /**
     * 测试StringBuffer & StringBuilder
     */
    @Test
    public void testStringBuffer(){
        StringBuffer  sf = new StringBuffer();
        System.out.println(sf == null);
        System.out.println(sf.append("xxx"));

        StringBuilder sb = new StringBuilder();
        System.out.println(sb == null);
        System.out.println(sb.append("yyy"));

    }
}
