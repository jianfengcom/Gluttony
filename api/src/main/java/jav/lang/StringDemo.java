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
public class StringDemo {

    public static void main(String[] args) {
        spaceEncode(null);
    }

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
}
