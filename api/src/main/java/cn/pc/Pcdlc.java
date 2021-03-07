package cn.pc;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Pcdlc {

    /**
     * ##: 360站点的软件终端页的下载链接
     *
     * @Description 从360的软件终端页-pc获取apk下载链接 修改为 wap
     * @Author chenjianfeng1
     * @Date 2021/1/15
     * @From
     * @Function AssistantFor360AndroidSpiderTask.java
     * @Version 1.0
     */
    public static String getWapUrl() {
        /*
            爬虫 - 爬取安卓软件终端页
            源站点: 360手机助手
         */
        String url = "http://zhushou.360.cn/detail/index/soft_id/4036154";
        String wapHost = "http://m.app.so.com";
        int startStr = url.indexOf("/soft_id/");
        String number = url.substring(startStr).replace("/soft_id/", "");
        return wapHost + "/detail/&id=" + number;
    }

    /**
     * ##: url空格
     *
     * @Description
     * @Author chenjianfeng1
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static void spaceEncode(String original) {
        if (original == null)
            original = "https://ftp.pconline.com.cn/pub/download/201010/maldner_20200831/terminator/Microsoft Edge浏览器_84.0.522.61@1918_372423.exe?md5=RYhN34QCiOqfcoyqxG9z5A&expires=1599213920";

        URL url = null;
        try {
            url = new URL(original);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //  path=/pub/download/...
        String path = url.getPath();

        String headPath = path.substring(0, path.lastIndexOf("/") + 1);
        String lastPath = null;
        try {
            lastPath = URLEncoder.encode(path.substring(path.lastIndexOf("/") + 1), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String targetPath = headPath + lastPath.replace("+", "%20"); // 解决方案: %20替换+

        System.out.println(path);
        System.out.println(headPath + lastPath);
        System.out.println(targetPath);

    }

}
