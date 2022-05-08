package others;

import Utils.GZIPUtil;
import Utils.MyFileUtil;
import Utils.MyTimeUtil;
import cn.hutool.core.io.FileUtil;
import entity.ConstantConfig;
import entity.User;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:KUN
 * @Data:2021/7/6 15:42
 * @Description: 测试File类
 * @Version:1.0
 */
@Slf4j
public class FileTest {

    //测试截取文件路径
    @Test
    public void FilePathTest() throws IOException {
        File file = new File("D:/WeChatDocument/WeChat Files/wxid_heggacdjx86m51/FileStorage/File/2021-07/FileUtil.java");
        System.out.println("getpath"+file.getPath());
        System.out.println("getAbsolutePath"+file.getAbsolutePath());
        System.out.println("getCanonicalPath"+file.getCanonicalPath());
        //测试substring
        String path = file.getPath();
        System.out.println(path.substring(0, path.indexOf(".")-4)+"0001");
        path = "F:/Java/IdeaProjects/tmp/uploadLogs/10800_ESURFING_SSOLOG_20210706_20210705_D_00_0001.DAT";
        System.out.println(path.substring(path.lastIndexOf("/")+1));
    }

    //测试文件夹路径
    @Test
    public void testFilePath() {
        File fils = new File("F:/Java/IdeaProjects/tmp/uploadLogs/");
        System.out.println(fils.listFiles().length);
        File fils1 = new File("F:/Java/IdeaProjects/tmp/uploadLogs");
        System.out.println(fils1.listFiles().length);
    }

    /**测试写入文件前如果文件不存在需要创建吗？
     * 结论：
     *      1、当文件名之前的所有文件夹路径都存在但是文件不存在时，写入时会自动创建txt文件
     *      2、当文件名之前的文件夹存在未创建的文件夹时，比如 uploadPath文件夹缺失
     *          写入会报错：java.io.FileNotFoundException
     */
    @Test
    public void writeWithoutFile() {
        try {
            // 使用文件名称创建流对象
            FileOutputStream fos = new FileOutputStream("F:/Java/IdeaProjects/tmp/mesh/uploadPath/fos.txt", true);
            // 字符串转换为字节数组
            byte[] b = "abcde".getBytes();
            // 写出从索引2开始，2个字节。索引2是c，两个字节，也就是cd。
            fos.write(b);
            // 关闭资源
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试文件夹缺失的情况下，file.createNewFile()方法能否同时创建文件和文件夹
     *  结论：
     *      只能创建文件无法创建文件夹
     */
    @Test
    public void testCreateFile() throws FileNotFoundException {
        // 使用文件名称创建流对象
        File file = new File("F:/Java/IdeaProjects/tmp/mesh/uploadPath/fos.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        FileOutputStream fos = new FileOutputStream(file, true);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 空对象获取元素报空指针异常
     */
    @Test
    public void getEmpty(){
        User u1 = null;
        String a = u1.userName;
        System.out.println(a);
    }

    /**
     * 测试全屋wifi的pdf生成系统文件解压的过程
     * @param path
     * @return
     * @throws IOException
     */
    public List<String> unGzip(String path) throws IOException {
        //log.info("解析文件夹的路径为{}", path);
        List<String> list = new ArrayList<>();
        String curDate = MyTimeUtil.getSysDate();
        List<File> files = getFiles(path);
        for (int i = 0; i < files.size(); i++) {
            String zipFileName = files.get(i).getName();
            //log.info("开始解压文件 {}", zipFileName);
            long startTime = System.currentTimeMillis();
            String fileName = "data_" + curDate + "_" + i+".txt";
            //每个压缩文件解压后对应一个txt文件
            GZIPUtil.decompressByGZip(files.get(i), fileName);
            list.add(MyFileUtil.combine(path, fileName));
            long endTime = System.currentTimeMillis();
            //log.info("结束解压文件，花费时间{}", (endTime - startTime));
            //log.info("删除文件 {}", ConstantConfig.FILE_SERVER + zipFileName);
            FileUtil.del(path + zipFileName);
        }
        return list;
    }
    public List<File> getFiles(String path) {
        List<File> list = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.getName().endsWith("gz")) {
                //log.info("文件名字{}", f.getName());
                list.add(f);
            }
        }
        return list;
    }
}
