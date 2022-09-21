package others;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;

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

    /**
     * 测试 "|"作为分隔符分割string
     */
    @Test
    public void testSplit(){
        String apString = "HUAWEI|TC7102|60AAEF9F7395|VER.A|10.0.5.60(SP9C30)|0|745040|V2019.1.0";
        String[] apMac = apString.split("\\|", -1);
        System.out.println(Arrays.toString(apMac));

        String patternMac="^([0-9a-fA-F]{2})(([0-9a-fA-F]{2}){5})$";
        System.out.println("--" + apMac[2]+"--");
        System.out.println(apMac[2].matches(patternMac));
        System.out.println(Pattern.matches(patternMac,apMac[2]));

    }
}
