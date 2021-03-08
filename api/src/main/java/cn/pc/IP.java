package cn.pc;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IP {

    /**
     * ##: IP
     *
     * @param request 请求对象
     * @Description 获取客户端的IP
     * @Author
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP-X-FORWARDED-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip.length() > 15) {
            String targetIp = getTargetIp(ip);
            if (targetIp != null)
                return targetIp;
        }
        return ip;
    }

    public static String getTargetIp(String source) {
        if (source == null) {
            source = "unknown, 120.240.100.33, 192.168.238.25, 192.168.238.153";
        }
        String[] ipList = source.split(",");
        for(int index = 0; index < ipList.length; index ++){
            String ip = ipList[index].trim();
            if(!("unknown".equalsIgnoreCase(ip))){
                return ip;
            }
        }
        return null;
    }

    public static final Boolean USER_CACHES = false;

    public static final int CONNECT_TIMEOUT = 3000;

    public static final int READ_TIMEOUT = 3000;

    public static final String DOMAIN = ".jd.com";

    //获取爬虫代理ip的应用 最后必须/结尾
    //public static final String PROXY_URL = "http://proxy.pc.com.cn/";

    //获取爬虫代理ip的应用，线上环境如域名不行则用ip 最后必须 / 结尾
    public static final String PROXY_URL = "http://192.168.12.98/";

    public static final String USER_AGENT = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";

    public static final int RECONNECT_MAX_TIMES = 3;

    public static int reconnect_time = 1;

    public static String sourceUrl = "";

    /**
     * 获取代理IP
     *
     * @param url 抓取url
     * @param domain 抓取源站域名
     * @return
     * @author HuoMingxiang, 2017年10月20日.
     */
    public static String httpGet(String url, String domain)throws Exception {
        if (!url.equals(sourceUrl)) {
            sourceUrl = url;
            reconnect_time = 1;
        }
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) getConnect(url, domain);
            int status = connection.getResponseCode();
            if (status == 503) {
                System.out.println("请求频繁，代理ip有被封的风险，请降低抓取速率！，status:" + status);
                throw new Exception("请求频繁，代理ip有被封的风险，请降低抓取速率！，status:" + status);
            } else if (status >= 400) {
                if (reconnect_time < RECONNECT_MAX_TIMES) {
                    reconnect_time++;
                    Thread.sleep(1000);
                    return httpGet(url, domain);
                }

                //System.out.println("read fail, status:" + status);
                throw new Exception("read fail, status:" + status);

            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            //System.out.println("read success, status:" + connection.getResponseCode());
        } catch (MalformedURLException e) {
            //System.err.println("MalformedURL ERROR");
            throw new Exception("MalformedURL ERROR");
        } catch (IOException e) {
            //System.err.println("IO Error");
            throw new Exception("IO Error");
        } catch (InterruptedException e) {
            //System.err.println("Thread error");
            throw new Exception("Thread error");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    //System.err.println("Inputstream close fail");
                    throw new Exception("Inputstream close fail");
                }
            }
        }
        return sb.toString();

    }

    /**
     * 获取连接对象
     *
     * @param url,domain
     * @return
     * @author HuoMingxiang, 2017年10月27日.
     */
    public static HttpURLConnection  getConnect(String url, String domain)throws Exception {
        URL realUrl;
        try {
            String urlStr = PROXY_URL+"?url=" + java.net.URLEncoder.encode(url,"UTF-8") ;
            realUrl = new URL(urlStr);

            HttpURLConnection connection = (HttpURLConnection )realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("X-Domain", domain);
            connection.setRequestProperty("user-agent", USER_AGENT);
            connection.setRequestMethod("GET");// 提交模式

            // 建立实际的连接
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setUseCaches(USER_CACHES);
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.connect();

            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            //System.err.println("connect time out");
            throw new Exception("connect time out！");

        }

    }

    public static void main(String[] args) {
        String host = "apps.apple.com";
        String ip = "https://apps.apple.com/cn/app/id1555608868";

        String ret = null;
        try {
            ret = httpGet(host, ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("ret=" + ret);
    }
}
