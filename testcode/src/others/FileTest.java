package others;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author:KUN
 * @Data:2021/7/6 15:42
 * @Description: 测试File类
 * @Version:1.0
 */
public class FileTest {

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

    @Test
    public void testFilePath(){
        File fils = new File("F:/Java/IdeaProjects/tmp/uploadLogs/");
        System.out.println(fils.listFiles().length);
        File fils1 = new File("F:/Java/IdeaProjects/tmp/uploadLogs");
        System.out.println(fils1.listFiles().length);


    }
}
