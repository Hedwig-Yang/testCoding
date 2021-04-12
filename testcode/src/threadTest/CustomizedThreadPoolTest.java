package threadTest;

import org.apache.commons.lang3.StringUtils;

import java.io.Console;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther:KUN
 * @Data:2021/3/17 18:20
 * @Description:
 * @Version:自定义创建线程
 */
public class CustomizedThreadPoolTest {
    public static void main(String[] args) {
        test(10);
    }

    public static void test(int size) {
        /*自定义创建线程池，核心线程数5，最大线程数为20，允许空闲时间2秒，使用阻塞队列LinkedBlockingQueue，并设置长度为5，
        在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到核心线程数5条后，
        就会把到达的任务放到缓存队列当中，当换队列存储任务满了以后，开启新的线程来执行新添加的任务，直至线程数达到线程池最大线程数，
        当达到maximumPoolSize，后依然有任务过来时，（此时队列和线程池都是满的）将触发终止策略。*/
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 20,
                2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));

        for (int i = 0; i < size; i++) {
            poolExecutor.execute(new DemoTask(i));
            System.out.println("poolSize:" + poolExecutor.getPoolSize());
            System.out.println("corePoolSize:" + poolExecutor.getCorePoolSize());
            System.out.println("maximumPoolSize:" + poolExecutor.getMaximumPoolSize());
            System.out.println("queue:" + poolExecutor.getQueue().size());
            System.out.println("completedTaskCount:" + poolExecutor.getCompletedTaskCount());
            System.out.println("largestPoolSize:" + poolExecutor.getLargestPoolSize());
            System.out.println("keepAliveTime:" + poolExecutor.getKeepAliveTime(TimeUnit.SECONDS));
        }

        poolExecutor.shutdown();
    }

    static class DemoTask implements Runnable {

        private int taskNum;

        public DemoTask(int taskNum) {
            this.taskNum = taskNum;
        }

        @Override
        public void run() {
            System.out.println(StringUtils.center("正在执行" + taskNum, 20, "="));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(StringUtils.center("执行完毕" + taskNum, 20, "="));
        }
    }
}
