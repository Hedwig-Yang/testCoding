package Utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class MyFileUtil {
    public static String combine(String path1, String path2)

    {
        File file1 = new File(path1);

        File file2 = new File(file1, path2);

        return file2.getPath();

    }

    public static void deleteFile(String path) throws IOException {
        Path path1 = Paths.get(path);
        Files.delete(path1);
    }

}
