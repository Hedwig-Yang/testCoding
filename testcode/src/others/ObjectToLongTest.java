package others;

import java.util.HashMap;
import java.util.Map;

public class ObjectToLongTest {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 111000);

        System.out.println(Long.valueOf(String.valueOf(map.get("code"))));
        System.out.println(Long.parseLong(String.valueOf(map.get("code"))));
        System.out.println(Long.valueOf(String.valueOf(map.get("code"))).longValue());
        System.out.println(map.get("lin"));
    }
}
