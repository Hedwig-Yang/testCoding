package threadTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author:Z
 * @Date:2022/3/25 18:22
 * @Description: 测试线程池接口ExecutorService的submit方法和execute方法
 * @Version:1.0
 */

class ThreadRun implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

/**
 * Callable：带泛型的接口，泛型指的是方法的返回值类型,同时call方法可能抛出异常。
 */
class ThreadCall implements Callable<Integer>{
    private int num;
    public ThreadCall(int num){
        this.num = num;
    }
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return num+1;
    }
}

public class submitFunctionTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try{
            Future<Integer> submitResult = executorService.submit(new ThreadCall(5));
            Integer resultNum = submitResult.get();
            System.out.println(resultNum);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}


//executorService.execute(new ThreadRun());

