package cn.pc;

import or.apache.commons.httpclient.HttpClientUtil;

import java.io.UnsupportedEncodingException;

public class NewSpider {

    /*
        代理服务器
        public static String PROXY_URL = "http://t-proxy.pc.com.cn/";
        public static String PROXY_URL = "http://192.168.12.98/"; // 线上环境
     */
    public static String PROXY_URL = "http://proxy.pc.com.cn/";

    /**
     * ##: 获取代理IP
     *
     * @param url 抓取url
     * @Description
     * @Author
     * @Date
     * @From ProxyIpUtils.java
     * @Function
     * @Version 1.0
     */
    public static void getHost(String url) {
        try {
            url = url + "?" + System.currentTimeMillis();
            String realUrl = PROXY_URL + "?url=" + java.net.URLEncoder.encode(url, "UTF-8");

            // 获取代理IP
            HttpClientUtil.getMethod(realUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
