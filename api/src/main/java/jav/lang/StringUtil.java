package jav.lang;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @Description:
 * @Author
 * @Date 2020/11/10
 * @Version 1.0
 */
public class StringUtil {

    /**
     * key=空格编码问题
     *
     * ps: pconline-dl
     *
     * @param spec
     * @throws Exception
     * @Author cjf-pc
     */
    public static void spaceEncode(String spec) {
        if (spec == null)
            spec = "https://ftp.pconline.com.cn/pub/download/201010/maldner_20200831/terminator/Microsoft Edge浏览器_84.0.522.61@1918_372423.exe?md5=RYhN34QCiOqfcoyqxG9z5A&expires=1599213920";

        URL url = null;
        try {
            url = new URL(spec);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String path = url.getPath(); //  path=/pub/download/...

        String headPath = path.substring(0, path.lastIndexOf("/") + 1);
        String lastPath = null;
        try {
            lastPath = URLEncoder.encode(path.substring(path.lastIndexOf("/") + 1), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String targetPath = headPath + lastPath.replace("+", "%20"); // 解决方案: %20替换+

        System.out.println(path);
        System.out.println(targetPath);
        System.out.println(headPath + lastPath);
    }

    /**
     * key=分隔字符串
     *
     * @param source
     * @param regex
     */
    public static void split(String source, String regex) {
        if (regex == null) {
            regex = "/";
        }
        String[] split = source.split(regex);
        System.out.println("length=" + split.length);
        for (int i = 0; i < split.length; i++) {
            System.out.println(i + ":" + split[i]);
        }
    }

    /**
     * key=按指定字节长度截取字符串
     * 家居网-品牌库(pchouse-product)
     *
     * @param source
     * @param num
     * @return
     * @Author cjf-pc
     */
    public static String sub(String source, int num) {
        if (source == null || source.getBytes().length <= num) {
            return source;
        }
        return split(source, num - 3) + "...";
    }

    public static String split(String source, int num) { // 1021
        // 记录已经截取的字节
        StringBuffer sb = new StringBuffer();
        // 记录已经截取的字节长度
        int len = 0;

        String temp = null;
        for (int i = 0; i < num && len < num; i++) {
            // 获取单个字符
            temp = String.valueOf(source.charAt(i));
            // 获取单个字符的字节数,累加
            len += temp.getBytes().length;

            if (len > num) {
                break; // 超出的字符不要
            }

            // 拼接到目标字符串上
            sb.append(temp);
        }
        return sb.toString();
    }
}
