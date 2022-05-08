package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTimeUtil {
    public static String getSysDate(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
    }
    public static String getSysTime(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
    }

    public static String getSysTime1(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));

    }
}
