package cn.pc;

import jav.text.DateFormatUtil;
import jav.util.DateUtil;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Date;

public class ProductWx {

    /**
     * ##: 推 电脑网百度小程序的api
     *
     * @Description 1000条生成1个文件
     * @Author chenjianfeng1
     * @Date
     * @From 电脑网-产品库微信小程序(pconline-pdwx)
     * @Function
     * @Version 1.0
     */
    public static void file2Files(Date start, int number) {
        long startTime = System.currentTimeMillis();
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/data/baidu/build_api.txt"));

            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isEmpty(line.trim())) {
                    continue;
                }

                if (count % number == 0) {
                    if (count != 0)
                        start = DateUtil.next(start, DateUtil.NEST);

                    String fullName = "/data/baidu/submit_api" +
                            DateFormatUtil.format(start, "yyyy-MM-dd") + ".txt";

                    // 文件如果不存在, 就新建
                    File file = new File(fullName);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    if (writer != null) {
                        writer.flush();
                        writer.close();
                    }
//                    writer = new BufferedWriter(new FileWriter(fullName));
                    writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(file, true), "UTF-8"));
                }
                writer.write(line.trim());
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
