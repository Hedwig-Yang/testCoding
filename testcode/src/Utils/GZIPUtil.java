package Utils;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;

@Slf4j
public class GZIPUtil {
    /**
     * 使用GZIP算法进行解压.
     * @return 解压是否成功.
     * @throws @throws Exception 解压过程中可能发生的异常.
     */
    public static String decompressByGZip(File file, String fileName) throws IOException {
        // 变量定义.
        FileInputStream sourceGZipFileFis = null;
        BufferedInputStream sourceGZipFileBis = null;
        FileOutputStream targetFileFos = null;
        GzipCompressorInputStream sourceGZipFileGcis = null;
        String resultFile = null;
        try {
            // 解压变量初始化.
            sourceGZipFileFis = new FileInputStream(file);
            sourceGZipFileBis = new BufferedInputStream(sourceGZipFileFis);
            sourceGZipFileGcis = new GzipCompressorInputStream(sourceGZipFileBis);
            resultFile = FileUtil.getParent(file,1)+ "/" + fileName;
            targetFileFos = new FileOutputStream(resultFile) ;
            // 采用commons-compress提供的方式进行解压.
//            targetFileFos.write(IOUtils.toByteArray(sourceGZipFileGcis));
            IOUtils.copy(sourceGZipFileGcis,targetFileFos);
        } catch (Exception ex) {
           // log.info("GZipDiskUtil.decompressByGZip.", ex);
            return resultFile;
        } finally {
            if (sourceGZipFileGcis != null)
                sourceGZipFileGcis.close();
            if (sourceGZipFileBis != null)
                sourceGZipFileBis.close();
            if (sourceGZipFileFis != null)
                sourceGZipFileFis.close();
            if (targetFileFos != null)
                targetFileFos.close();
        }
        return resultFile;
    }
}
