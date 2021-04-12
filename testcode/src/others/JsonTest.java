package others;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    @Test
    public void test1() {
        List<String> l = new ArrayList<>();
        String a = "{province:\"山东\",data:[9, 0]}";
        String b = "{province:\"上海\",data:[54, 42]}";
        l.add(a);
        l.add(b);
        JSONObject jsonObject = JSON.parseObject(a);
        System.out.println(jsonObject.get("data"));
    }

    @Test
    public void test2(){
        JSONObject es = new JSONObject();
        es.put("num",123);
        String num1 = es.getString("num1");
        System.out.println(num1==null);
    }

    @Test
    public void test3(){
        String s ="{\"781D4AD84460\": {\"label\": \"RA|RC\", \"type\": 1, \"brand\": \"ZTE\", \"model\": \"ZXHN F450\", \"isdual\": 2, \"isge\": 2, \"lowdev\": \"3\", \"wipoint\": 82.0, \"historybestchannel\": 7, \"currentchannel\": 8, \"cpuusedetail\": [0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], \"memusedetail\": [0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0], \"cputempdetail\": [12], \"pontempdetail\": [0, 44.0, 45.0, 45.0, 45.0, 45.0, 45.0, 45.0, 45.0, 45.0, 45.0, 44.0, 44.0], \"onlinetime\": 641050, \"lowfreq\": 0.0, \"totalfreq\": 993.0, \"lowcovrageperc\": 0.0}, \"line\": {\"label\": \"\"}, \"flow\": {\"label\": \"\"}}";
        JSONObject json = JSONObject.parseObject(s);
        System.out.println(json.toJSONString());
    }


}
