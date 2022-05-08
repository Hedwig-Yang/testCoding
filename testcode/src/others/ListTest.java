package others;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Z
 * @Date:2022/3/31 14:13
 * @Description: 测试List
 * @Version:1.0
 */
public class ListTest {


    /**
     * 测试list的 subList方法，超过下限报错
     */
    @Test
    public void testSubList1() {
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        List listSub = list.subList(0, 5);
        System.out.println(listSub);
    }

}
