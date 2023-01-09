package threadTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author:Z
 * @Date:2023/1/6 16:28
 * @Description: 多线程环境调试测试
 *         打断点，将其设置为Thread模式。这个很重要，“Thread” 模式代表所有的线程运行到此处都会停止，
 *         而如果设置成 “All” 模式的话，则是当有一个线程运行到此处就所有线程都停止，其他的线程停在哪儿就不一定了。
 * @Version:1.0
 */
@Slf4j
public class ThreadDebug {

    // 锁
    private Object lock = new Object();

    // 执行方法 互斥
    public void execute() {
        /*
        //有锁的情况
        synchronized (lock) {
            String name = Thread.currentThread().getName();
            System.out.println(name + " Hello World!");
        }*/

        //无锁的情况
        int i = 0;
        while(i < 120){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("子线程{}正在运行第{}秒",Thread.currentThread().getName(),(i+1));
        }

    }

    // 启动3个线程，3个线程持有同一个DebugDemo对象
    public static void main(String[] args) {
        ThreadDebug demo = new ThreadDebug();
        Thread t1 = new Thread(new Task(demo), "t1");
        Thread t2 = new Thread(new Task(demo), "t2");
        Thread t3 = new Thread(new Task(demo), "t3");
        t1.start();
        t2.start();
        t3.start();
        int i = 0;
        while(i < 120){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("主线程{}正在运行第{}秒",Thread.currentThread().getName(),(i+1));
        }

    }

    // 线程实体
    static class Task implements Runnable {
        private ThreadDebug demo;
        public Task(ThreadDebug demo) {
            this.demo = demo;
        }
        @Override
        public void run() {
            demo.execute();
        }
    }

}
