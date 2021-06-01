package jav.io;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    /*
        ##: 读取File
     */
    public static String readFileContent(File file) {
        StringBuffer result = null;
        BufferedReader bufferedReader = null;

        FileReader fileReader;
        try {
            File target = new File("/temptemptemp/fk-question0601.sql");
            if(!target.exists()) {
                try {
                    target.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            result = new StringBuffer();
            // 创建一个读取文件的流对象 FileReader
            // 用BufferedReader封装一下，读取效率更高
            bufferedReader = new BufferedReader(new FileReader(file));

            /*
                bufferedReader.readLine(): 读取文件的一行，如果有多行，会逐行读取。
             */
            String line;
            List<String> tempList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                JSONObject obj = JSON.parseObject(line);
                if (obj.containsKey("wySuggestion") &&  (!"PASS".equals(obj.getString("wySuggestion")))) {
                    long id = obj.getLongValue("id");
                    String tableName = obj.getString("tableName");

                    System.out.println(obj.getString("uri") + " - " + id);

                    // 获取ID
                    tempList.add("UPDATE " + tableName + " SET status = -1 WHERE status > 0 AND id = " + id + ";");
                }

                /*
                一代目

                Pattern pattern = Pattern.compile("//product.pconline.com.cn/pdlib/(\\d+)_comment_id(\\d+).html");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String group = matcher.group();
                    if (true || group.contains("DELETE") || group.contains("REVIEW")) {
                        int start = group.indexOf("_comment_id");
                        int end = group.indexOf(".html");

                        String s = group.substring(start + 11, end);
                        System.out.println("UPDATE cmt_comment SET reviewStatus = -1  WHERE reviewStatus = 1 AND cmtCommentId = " + s + ";");
                    }
                }*/

                // 控制台
                // System.out.println(line);
            }
            if (!tempList.isEmpty()) {
                WriterUtil.writeFile(target, tempList);
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

    public static void main(String[] args) throws IOException {
        System.out.println(2333);
        readFileContent(new File("D:/temptemptemp/house/censor_house_fk_question_2021.txt_wy_out"));
        System.out.println(666);
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


