package others;

import java.util.regex.Pattern;

/**
 * @Auther:KUN
 * @Data:2021/3/19 14:58
 * @Description:
 * @Version:
 */
public class MacCheckoutTest {
    public static void main(String[] args) {
        String PATTERN_MAC="^[A-F0-9]{2}([A-F0-9]{2}){5}$";
        System.out.println(Pattern.matches(PATTERN_MAC, "F89A78B0F5E5" ));
    }
}
