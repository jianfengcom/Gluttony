package jav.io;

import jav.text.DateFormatUtil;
import jav.util.DateUtil;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @Description:
 * @Author
 * @Date 2020/11/13
 * @Version 1.0
 */
public class FileUtil {

    /**
     * key=读取文件, 并打印到控制台
     *
     * @param file
     * @throws Exception
     */
    public static void readLine(File file) throws Exception {
        // 创建一个读取文件的流对象
        FileReader fileReader = new FileReader(file);
        // 用BufferedReader封装一下，读取效率更高
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        // 定义一个空串
        String str;
        // bufferedReader.readLine()是读取文件的一行，如果有多行，会逐行读取。
        // 当每一行不为空时则把内容打印到控制台中; 也可以存到写入的流中，把内容写到文本里
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        // 最后不要忘记关闭 流
        fileReader.close();
    }

    /**
     * key=读取文件
     *
     * @param file
     * @return 文件文本内容
     */
    public static String readFileContent(File file) {
        StringBuffer stringBuffer = new StringBuffer();

        BufferedReader reader = null;
        FileReader fileReader;
        try {
            fileReader = new FileReader(file); // 创建一个读取文件的流对象
            reader = new BufferedReader(fileReader); // 用BufferedReader封装一下
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                stringBuffer.append(tempStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return stringBuffer.toString();
    }

    /**
     * key=读取文件
     * 读取指定的行的内容
     * ps: 记得控制台键入指定的行数
     *
     * @param fileName
     * @throws IOException
     */
    public static void readLine(String fileName) throws IOException {
        int lines = getTotalLines(fileName); // 获取文件的内容的总行数
        System.out.println("本文总共有: " + lines + "行");

        while (true) {
            Scanner sc = new Scanner(System.in);
            int lineNumber = sc.nextInt(); // 指定读取的行号
            readLineVarFile(fileName, lineNumber); //读取指定行的内容
        }
    }

    // 文件内容的总行数
    private static int getTotalLines(String fileName) throws IOException {
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

    // 输出本行内容
    private static void readLineVarFile(String fileName, int lineNumber) throws IOException {
        // 使用缓冲区的方法将数据读入到缓冲区中
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String contentLine = bufferedReader.readLine(); // 行内容
        if (lineNumber <= 0 || lineNumber > getTotalLines(fileName)) { // 确定输入的行数是否有内容
            System.out.println("不在文件的行数范围之内。");
        }
        int num = 0;
        while (contentLine != null) {
            if (lineNumber == ++num) {
                System.out.println("第" + lineNumber + "行: " + contentLine);
            }
            contentLine = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    /**
     * key=读取文本每一行内容并用分隔符(,) 连接
     * 电脑网-产品库微信小程序(pconline-pdwx)
     *
     * @return
     * @throws Exception
     * @Author cjf-pc
     */
    public static String read() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("/data/baidu/submit_api.txt"));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            if (StringUtils.isEmpty(str.trim())) {
                continue;
            }
            sb.append(str.trim() + ",");
        }
        String urls = sb.toString();
        urls = urls.substring(0, urls.length() - 1);
        reader.close();
        return urls;
    }

    // 写入
    public static void write(List<String> urlList) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("/data/baidu/build_api.txt"));
        for (String url : urlList) {
            writer.write(url);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    /**
     * key=将1个txt拆分成若干个txt
     *
     * @param start 日期
     */
    public static void file2File(Date start) {
        long startTime = System.currentTimeMillis();
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/data/baidu/build_api.txt"));
            String str;
            int count = 0;
            while ((str = reader.readLine()) != null) {
                if (StringUtils.isEmpty(str.trim())) {
                    continue;
                }
                if (count % 1000 == 0) {
                    if (count != 0)
                        start = DateUtil.next(start, DateUtil.ONE_DAY);

                    String fullName = "/data/baidu/submit_api" +
                            DateFormatUtil.format(start, "yyyy-MM-dd") + ".txt";

                    // 文件如果不存在, 就新建
                    File tempFile = new File(fullName);
                    if (!tempFile.exists()) {
                        tempFile.createNewFile();
                    }
//                    writer = new BufferedWriter(new FileWriter(fullName));
                    if (writer != null) {
                        writer.flush();
                        writer.close();
                    }
                    writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(tempFile, true), "UTF-8"));
                }
                writer.write(str.trim());
                writer.newLine();
                count++;
            }
            System.out.println("写入文件耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


