package cn.pconline;

import or.apache.commons.httpclient.HttpClientUtil;

import java.io.UnsupportedEncodingException;

/**
 * @Description: 下载新爬虫
 * @Author
 * @Date 2020/11/28
 * @Version 1.0
 */
public class NewSpider {

    /*
        key=打印代理IP
        public static String PROXY_URL = "http://t-proxy.pc.com.cn/";
        public static String PROXY_URL = "http://192.168.12.98/"; // 线上环境
     */
    public static String PROXY_URL = "http://proxy.pc.com.cn/";

    public static void getHost() {
        String url = System.currentTimeMillis() + "";
        try {
            url = url + "?" + System.currentTimeMillis();

            String urlStr = PROXY_URL + "?url=" + java.net.URLEncoder.encode(url, "UTF-8");
            System.out.println("urlStr=" + urlStr);

            HttpClientUtil.getMethod(urlStr); // 打印代理IP
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
