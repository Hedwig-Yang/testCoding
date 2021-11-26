package others;

import org.junit.Test;

import java.util.ArrayList;

public class LocalVar {

    public int i;

    @Test
    public void test(){
        i = 2;
        souti();
    }
    public void souti(){
        System.out.println(i);
    }

    public static void main(String[] args) {
        String sameUpperGw = "";
        ArrayList<String> stringStore = new ArrayList<>();
        stringStore.add("abc");
        stringStore.add("abc");
        stringStore.add("abc");
        stringStore.add("abc");

        stringStore.add("bcd");
        stringStore.add("bcd");
        stringStore.add("bcd");
        stringStore.add("bcd");

        stringStore.add("cde");
        stringStore.add("cde");
        stringStore.add("cde");
        stringStore.add("cde");
        stringStore.add("cde");

        stringStore.add("def");
        stringStore.add("def");
        stringStore.add("def");
        stringStore.add("def");
        for (int i = 0;i<stringStore.size();i++){
            if("".equals(sameUpperGw) || stringStore.get(i).equals(sameUpperGw)){
                System.out.println("相同时添加");
                sameUpperGw = stringStore.get(i);
            }else{
                System.out.println("不同时执行");
                sameUpperGw = stringStore.get(i);
            }
        }
    }
}
