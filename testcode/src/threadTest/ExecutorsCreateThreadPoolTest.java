package threadTest;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Auther:KUN
 * @Data:2021/3/17 15:03
 * @Description:Executors工厂方法创建
 * @Version:
 */
public class ExecutorsCreateThreadPoolTest {

    @Test
    public void test1(){
        //创建使用单个线程的线程池
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            es1.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行任务");
                }
            });
        }
    }

    @Test
    public void test2(){
        //创建使用固定线程数的线程池
        ExecutorService es2 = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            es2.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行任务");
                }
            });
        }
    }

    @Test
    public void test3(){
        //创建一个会根据需要创建新线程的线程池
        ExecutorService es3 = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            es3.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "正在执行任务");
                }
            });
        }
    }

    @Test
    public void test4()  {
        //创建拥有固定线程数量的定时线程任务的线程池
        ScheduledExecutorService es4 = Executors.newScheduledThreadPool(2);
        System.out.println("时间：" + System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            System.out.println("这儿");
            es4.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("时间："+System.currentTimeMillis()+"--"+Thread.currentThread().getName() + "正在执行任务");
                }
            },3, TimeUnit.SECONDS);
        }
        try {
            //Junit单元测试不支持多线程,简单粗暴地让主线程休眠一段时间，然后让子线程能够运行结束。
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        //创建只有一个线程的定时线程任务的线程池
        ScheduledExecutorService es5 = Executors.newSingleThreadScheduledExecutor();
        System.out.println("时间：" + System.currentTimeMillis());  //单例模式
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            es5.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("时间："+System.currentTimeMillis()+"--"+Thread.currentThread().getName() + "正在执行任务");
                    countDownLatch.countDown();//当前线程调用此方法，则计数减一
                }
            },3, TimeUnit.SECONDS);
        }
        System.out.println("主线程"+Thread.currentThread().getName()+"等待子线程执行完成...");
        try {
            //Junit单元测试不支持多线程,使用CountDownLatch工具类，让主线程阻塞，直到子线程运行结束或者阻塞超时
            countDownLatch.await(5, TimeUnit.MINUTES);//阻塞当前线程，直到计数器的值为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程"+Thread.currentThread().getName()+"开始执行...");
    }

}
