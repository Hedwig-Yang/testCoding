package others;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class SetToStringTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<String>();
        set.add("abc");
        set.add("bcd");
        set.add("cde");
        String arrays = set.toString();
        System.out.println(arrays);  //[bcd, abc, cde]
        arrays = Arrays.toString(set.toArray());  //[bcd, abc, cde]
        System.out.println(arrays);
    }

    @Test
    public void substringTest(){
        String date = "20210618";
        System.out.println(date.substring(0,6)+"10");

        String method = "com.ruoyi.project.system.menu.controller.MenuController.addSave()";
        method = method.substring(method.lastIndexOf(".")+1,method.indexOf("("));
        System.out.println(method);

        String authority = "admin" == "admin" ? "管理员":"普通";
        System.out.println(authority);

    }


}
