package others;

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
}
