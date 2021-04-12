package others;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger(0);
        num.getAndIncrement();
        System.out.println(num);
        num.addAndGet(3);
        System.out.println(num);
        num.addAndGet(-2);
        System.out.println(num);
        int a = 2;
        num.addAndGet(-a);
        System.out.println(num);
    }
}
