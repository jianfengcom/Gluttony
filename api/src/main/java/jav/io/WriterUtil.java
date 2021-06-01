package jav.io;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.List;

public class WriterUtil {

    /*
        ##: 写入File
     */
    public static void write(File file, String content) throws IOException {
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fos = new FileOutputStream(file, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write(content);
        osw.close();
    }

    public static void writeFile(File file, List<String> list) throws IOException {
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fw = new FileWriter(file, true); // 追加
            pw = new PrintWriter(fw);

            for (String obj : list) {
                try {
                    pw.println(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
            if (pw != null) {
                pw.flush();
            }
            if (fw != null) {
                fw.flush();
            }
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }

}
