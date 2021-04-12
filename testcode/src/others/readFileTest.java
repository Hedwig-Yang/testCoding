package others;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author:KUN
 * @Data:2021/3/31 17:24
 * @Description: 读取文件测试
 * @Version:1.0
 */
public class readFileTest {
    @Test
    public void readFileToArray(){
        String testString = "81201 2280082715    812 20210210 {'10D04016001':'A1','10D04016019':'中兴+ZXHNF450','10D04016020':'推荐499/699套餐+290*N+换网关(N=wifi数量-2)','10D04016039':'0','10D04016025':'1000','10D04016029':'2','10D04016038':'3','10D04016015':'6','10D04016004':'mac:E0383FE34C3D,ppoe:02280082715,elink_num:0,no_elink_route_num:1,bad_coverage_avg_frequency:0,band:200M,gateway_info:CTWRT|&|中兴|&|ZXHNF450|&|路由|&|single|&|1000|&|3,elink_info:,no_elink_route_info:,link_error:,label:A1','10D04016010':'0','10D04016023':'2.0','10D04016058':'15','10D04016024':'single','10D04016016':'10','10D04016008':'1','10D04016012':'0','10D04016018':'E0383FE34C3D','10D04016013':'1'} {}  ";
        String[] stringArray = testString.split("\u0005");
        for(int i = 0; i<stringArray.length;i++){
            System.out.println("stringArray["+i+"]: "+stringArray[i]);
        }
    }
}
