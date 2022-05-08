package others;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Z
 * @Date:2021/12/6 17:31
 * @Description: 测试父类子类
 * @Version:1.0
 */
public class FatherObject {


    /**
     * 测试instanceof()方法
     */
    @Test
    public void test1(){
        ArrayList al = new ArrayList<>();
        System.out.println(al instanceof List);
        System.out.println(al instanceof ArrayList);
    }
}
