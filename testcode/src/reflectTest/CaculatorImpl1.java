package reflectTest;

/**
 * @Author:KUN
 * @Data:2021/4/20 09:15
 * @Description: TODO
 * @Version:1.0
 */
public class CaculatorImpl1 implements Caculator {
    @Override
    public int add(int i,int j) {
        System.out.println("这是实现1");
        return i+j;
    }
}
