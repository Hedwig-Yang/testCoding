package jvmtest;

import java.util.ArrayList;

//-Xms 设置初始化内存分配大小 默认1/64
//-Xmx 设置最大分配内存，默认1/4
//-XX:+PrintGCDetails 打印GC垃圾回收信息

/*学习什么叫dump java异常文件：
    -XX:+HeapDumpOnOutOfMemoryError  DUMP oom的异常文件
*/

//-Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
public class UseJprofiler {
    byte[] array = new byte[1*1024*1024]; //1m

    public static void main(String[] args) {
        ArrayList<UseJprofiler> list = new ArrayList<>();
        int count = 0;
        try {
            while (true){
                list.add(new UseJprofiler()); //不停地把创建对象放进列表
                count = count + 1;
            }
        } catch (Error e) {
            System.out.println("count: "+count);  //问题所在
            e.printStackTrace();
        }

    }
}
