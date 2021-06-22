package others;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author:KUN
 * @Data:2021/5/8 09:55
 * @Description: java.text.ParseException: Unparseable date: "-"
 * @Version:1.0
 */

public class DateFormatTest {

    @Test
    public void test1(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            sdf.parse("-");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
