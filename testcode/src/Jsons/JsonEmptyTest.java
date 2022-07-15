package Jsons;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;


/**
 * @Author:Z
 * @Date:2021/11/26 16:02
 * @Description: Json相关测试
 * @Version:1.0
 */
public class JsonEmptyTest {

    /**
     * 判断JSONObject.parse()方法入参为空的情况
     */
    @Test
    public void jsonTest1(){
        JSONObject resultJson=(JSONObject) JSONObject.parse(null);
        //System.out.println(resultJson.toJSONString());   //java.lang.NullPointerException

        //测试获取没有的字段时的结果
        JSONObject resultJson2=(JSONObject) JSONObject.parse("{" +
                "\"a\":\"xyz\"," +
                "\"c\":null," +
                "\"d\":\"\"," +
                "\"e\":{}," +
                //"\"f\":{123!@#},"+   //不满足JsonString格式要求：com.alibaba.fastjson.JSONException: parse number key errorpos
                "\"g\":{\"xyz\":\"123\"}"+
                "}");
        String b = resultJson2.getString("b");     //Json中字段不存在
        JSONObject c = resultJson2.getJSONObject("c");  //Json中字段 = null
        JSONObject d = resultJson2.getJSONObject("d");  //Json中字段 = ""
        JSONObject e = resultJson2.getJSONObject("e");  //Json中字段 = {}
        JSONObject g = resultJson2.getJSONObject("g");
        System.out.println("b==null:"+(b == null)); /*true*/
        System.out.println("c==null:"+(c == null)); /*true*/
        System.out.println("d==null:"+(d == null)); /*true*/
        System.out.println("e==null:"+(e == null)); /*false*/
        System.out.println("g==null:"+(g == null)); /*false*/

        resultJson2.put("open" , 1);
        System.out.println("open : " + resultJson2.getString("open"));
        System.out.println("oooooooooo" + "" + b + "xxxxxx");
    }
}
