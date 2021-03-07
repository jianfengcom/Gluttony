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

    /*
        ##: 读取指定行
     */
    public static void readLine(String fileName) throws IOException {
        int lines = FileUtilHelp.getTotalLines(fileName); // 获取文件的内容的总行数
        System.out.println("本文行数:" + lines);

        while (true) {
            Scanner sc = new Scanner(System.in);
            int lineNumber = sc.nextInt(); // 指定读取的行号
            FileUtilHelp.readLineVarFile(fileName, lineNumber); //读取指定行的内容
        }
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


