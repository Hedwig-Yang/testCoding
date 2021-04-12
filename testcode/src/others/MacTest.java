package others;

import java.util.regex.Pattern;

/**
 * @Auther:KUN
 * @Data:2021/2/3 16:27
 * @Description:
 * @Version:
 */
public class MacTest {
    public static void main(String[] args) {
        String PATTERN_MAC="^[A-F0-9]{2}([A-F0-9]{2}){5}$";
        //44AEAB9F70DF
        boolean matches1 = Pattern.matches(PATTERN_MAC, "44AEAB9F70DF");
        boolean matches2 = stringIsMac("44AEAB9F70DF");
        System.out.println(matches1+" "+matches2);
    }

    private static boolean stringIsMac(String val) {
        String trueMacAddress = "([A-Fa-f0-9]{2}-){5}[A-Fa-f0-9]{2}";
        // 这是真正的MAV地址；正则表达式；
        if (val.matches(trueMacAddress)) {
            return true;
        } else {
            return false;
        }
    }

}
