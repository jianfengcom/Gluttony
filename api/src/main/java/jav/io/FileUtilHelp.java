package jav.io;

import java.io.*;

public class FileUtilHelp {

    /*
        文本内容总行数
     */
    public static int getTotalLines(String fileName) throws IOException {
        // 使用缓冲区的方法将数据读入到缓冲区中
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        LineNumberReader lineNumberReader = new LineNumberReader(bufferedReader);
        String contentLine = lineNumberReader.readLine(); // 行内容
        int lines = 0;
        while (contentLine != null) {
            lines++;
            contentLine = lineNumberReader.readLine();
        }
        lineNumberReader.close();
        bufferedReader.close();
        return lines; // 返回行数
    }

    /*
        输出指定行内容
     */
    public static String readLineVarFile(String fileName, int lineNumber) throws IOException {
        // 使用缓冲区的方法将数据读入到缓冲区中
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String contentLine = bufferedReader.readLine(); // 行内容

        if (lineNumber <= 0 || lineNumber > getTotalLines(fileName)) {
            System.out.println("输入行数有误!");
        }

        int num = 1;
        while (contentLine != null) {
            if (lineNumber == num++) {
                System.out.println("第" + lineNumber + "行: " + contentLine);
            }
            contentLine = bufferedReader.readLine();
        }
        bufferedReader.close();
        return contentLine;
    }

}
