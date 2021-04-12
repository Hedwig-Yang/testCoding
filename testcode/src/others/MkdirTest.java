package others;

import java.io.File;

public class MkdirTest {
    public static void main(String[] args) {
        String path = "d://testDir";
        File file = new File(path);
        if(!file.isDirectory()){
            file.mkdir();
        }
    }
}
