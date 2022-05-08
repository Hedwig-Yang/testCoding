package reflectTest;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author:KUN
 * @Data:2021/4/19 16:20
 * @Description: 测试反射
 * @Version:1.0
 */
public class reflectTest {

    @Test
    public void test1(){
        try {
            System.out.println(Class.forName("reflectTest.GetMethodClass").getMethod("method1", String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @description:测试对于同一个接口的两个实现类通过反射method.invoke(target,args)调用方法
     * Summary：实现类2的重写方法1.invoke(实现类1,args),无法执行 ;
     *          实现类1的重写方法1.invoke(实现类1,args),可以执行 ;公共接口的原始方法1.invoke(实现类1，args)可以执行；
     * @return: void
     * @author: KUN
     * @date: 2021/4/20 9:50
     */
    @Test
    public void test2(){
        Method add1 = null;
        Method add2 = null;
        try {
            add2 = Class.forName("reflectTest.CaculatorImpl2").getMethod("add", int.class, int.class);
            add1 = Class.forName("reflectTest.CaculatorImpl1").getMethod("add", int.class, int.class);
            Class c1 = Class.forName("reflectTest.CaculatorImpl1");

            Class<?>[] interfaces = Class.forName("reflectTest.CaculatorImpl2").getInterfaces();
            System.out.println(interfaces.length);
            Method interfaceAdd = interfaces[0].getMethod("add",int.class, int.class);

            Object[] args = {1,2};
            int result = (int)interfaceAdd.invoke(c1.newInstance(), args);
            System.out.println(result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
