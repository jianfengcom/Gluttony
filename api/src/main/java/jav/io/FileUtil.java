package jav.io;

import java.io.*;
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

    /*
        ##: 遍历指定文件下所有子文件(不包含子文件夹)
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
                    FileUtil.showFiles(f.getAbsolutePath());
                }
            }
        }
    }
}


