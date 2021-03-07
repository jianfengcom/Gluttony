package jav.io;

import jav.text.DateFormatUtil;
import jav.util.DateUtil;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileUtil {

    /*
        ##: 读取File
     */
    public static String readFileContent(File file) {
        StringBuffer result = null;
        BufferedReader bufferedReader = null;

        FileReader fileReader;
        try {
            result = new StringBuffer();
            // 创建一个读取文件的流对象 FileReader
            // 用BufferedReader封装一下，读取效率更高
            bufferedReader = new BufferedReader(new FileReader(file));

            /*
                bufferedReader.readLine(): 读取文件的一行，如果有多行，会逐行读取。
             */
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);

                // 控制台
                // System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 最后不要忘记关闭流
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return result.toString();
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
     * 电脑网-产品库微信小程序(pc-pdwx)
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

    /**
     * key=遍历指定文件下所有子文件 (not a directory)
     * @param path 文件夹绝对路径
     */
    public static void showFiles(String path){
        File file = new File(path);
        if(file.exists()){
            // 如果是文件，直接打印
            if(file.isFile()){
                // file.getAbsolutePath()获取对象的绝对路径（包含文件名）
                System.out.println(file.getAbsolutePath());
            }else{
                // 罗列出当前目录下所有的文件对象
                File[] files = file.listFiles();

                // 遍历当前目前下所有的文件对象
                // 递归
                for(File f : files){
                    System.out.println(f.getAbsolutePath());
                    FileUtil.showFiles(f.getAbsolutePath());
                }
            }
        }
    }
}


