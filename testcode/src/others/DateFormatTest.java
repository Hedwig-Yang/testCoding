package others;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Test
    public void test2(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = new Date();
            System.out.println(sdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
