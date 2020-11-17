package jav.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @Description:
 * @Author
 * @Date 2020/11/17
 * @Version 1.0
 */
public class WriterUtil {

    /**
     * key=Writer
     *
     * @param file
     * @param content
     * @throws IOException
     */
    public static void write(File file, String content) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write(content);
        osw.close();
    }

}
