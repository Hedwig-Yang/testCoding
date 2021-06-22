package others;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:KUN
 * @Data:2021/6/22 13:53
 * @Description: 测试Map相关操作
 * @Version:1.0
 */
public class MapTest {

    /**
     * @description:Map和List两者除(byte short int long float double boolean char)
     * 8种基本类型外的其他数据类型引用都为指针引用
     */
    @Test
    public void test1(){
        Map map = new HashMap();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1","a");
        jsonObject.put("2","b");
        map.put("json",jsonObject);
        JSONObject jo = (JSONObject) map.get("json");
        jo.remove("1");
        System.out.println(((JSONObject) map.get("json")).toJSONString());
    }
}
