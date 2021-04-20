package reflectTest;

/**
 * @Author:KUN
 * @Data:2021/4/20 09:17
 * @Description: TODO
 * @Version:1.0
 */
public class CaculatorImpl2 implements Caculator {
    @Override
    public int add(int i, int j) {
        System.out.println("这是实现2");
        return i+j+2;
    }
}
