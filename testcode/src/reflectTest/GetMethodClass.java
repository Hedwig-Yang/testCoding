package reflectTest;

import org.junit.Test;

import java.sql.SQLOutput;

/**
 * @Author:KUN
 * @Data:2021/4/19 16:16
 * @Description: TODO
 * @Version:1.0
 */
public class GetMethodClass {
    public static void method1(){
        System.out.println("this is method1");
    }
    public static void method1(String name){
        System.out.println("this is method1 with args");
    }

    @Test
    public void test1(){
        try {
            Class.forName("reflectTest.GetMethodClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("reflectTest.GetMethodClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
