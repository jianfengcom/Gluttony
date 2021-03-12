package cn.pc;

import or.apache.commons.httpclient.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * @From
     * @Function ProxyIpUtils.java
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

    /**
     * ##: 版本号比较
     *
     * @Description 0代表相等，1代表左边大，-1代表右边大
     * @Author
     * @Date
     * @From
     * @Function VersionAnalysis.java
     * @Version 1.0
     */
    public static int compareVersion(String v1, String v2) {
        System.out.println("==compareVersion==");
        System.out.println("v1:  " + v1 + " " + "v2:  " + v2);
        System.out.println("==compareVersion==");

        if (StringUtils.isBlank(v1)) {
            v1 = "0";
        }
        if (StringUtils.isBlank(v2)) {
            v2 = "0";
        }
        if (v1.equals(v2)) {
            System.out.println("result: " + 0);
            return 0;
        }

        Pattern pattern = Pattern.compile("[^0-9\\._]");
        Matcher v1Matcher = pattern.matcher(v1);
        Matcher v2Matcher = pattern.matcher(v2);
        v1 = v1Matcher.replaceAll("");
        v2 = v2Matcher.replaceAll("");
        String[] version1Array = v1.split("[._]");
        String[] version2Array = v2.split("[._]");

        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        long diff = 0;

        long left = 0;
        long right = 0;

        while (index < minLen
                && (diff = (left = StringUtils.isNotBlank(version1Array[index]) ? Long.parseLong(version1Array[index])
                : 0)
                - (right = StringUtils.isNotBlank(version2Array[index]) ? Long.parseLong(version2Array[index])
                : 0)) == 0) {
            index++;
        }

        long maxLen = Math.max(left, right);
        if (maxLen <= 2) {
            if (left - right > 0) {
                System.out.println("result: " + 1);
                return 1;
            } else if (left - right < 0) {
                System.out.println("result: " + -1);
                return -1;
            }
        }

        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if ((StringUtils.isNotBlank(version1Array[i]) ? Long.parseLong(version1Array[i]) : 0) > 0) {
                    System.out.println("result: " + 1);
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if ((StringUtils.isNotBlank(version2Array[i]) ? Long.parseLong(version2Array[i]) : 0) > 0) {
                    System.out.println("result: " + -1);
                    return -1;
                }
            }
            System.out.println("result: " + 0);
            return 0;
        } else {
            System.out.println("result: " + (diff > 0 ? 1 : -1));
            return diff > 0 ? 1 : -1;
        }
    }
}
