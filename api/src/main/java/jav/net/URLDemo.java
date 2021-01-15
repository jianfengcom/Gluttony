package jav.net;

import com.example.util.SSLUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

/**
 * @Description:
 * @Author
 * @Date 2020/11/6
 * @Version 1.0
 */
public class URLDemo {

    public static void main(String[] args) {
        // https://apps.apple.com/cn/app/id1437708921
        String imgPath = "https://is1-ssl.mzstatic.com/image/thumb/Purple113/v4/21/35/9d/21359d17-58ee-aa88-7dd5-61f097280ff3/pr_source.png/230x0w.webp";
        String targetFullPath = "/test/pcdlc/now.webp";
        try {
            downloadImg(imgPath, targetFullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * key=下载图片
     *
     * ps: 需要测试线上的, 才能出结果; 本地乱写代理配置都没问题
     * 线上测试: 下载新爬虫(pconline-dlNewSpider) 192.168.241.116:8081/cac/checki.do?img=&imgFullName=
     *
     * @param imgPath        图片链接: http和https处理方式略有不同
     * @param targetFullPath
     * @throws Exception
     * @Author 袁媛-pc
     */
    public static void downloadImg(String imgPath, String targetFullPath) throws Exception {
        URLConnection conn = null;

        // 设置代理服务
        Properties prop = System.getProperties();
        prop.setProperty("https.protocols", "TLSv1,SSLv3");
        prop.setProperty("http.proxySet", "true");
        prop.setProperty("http.proxyHost", "192.168.239.200");
        prop.setProperty("http.proxyPort", "1080");

        prop.setProperty("https.proxySet", "true");
        prop.setProperty("https.proxyHost", "192.168.239.200");
        prop.setProperty("https.proxyPort", "1080");

        // String password = "geturl:nBgWE8kLuLqhs"; _c
        URL url = new URL(imgPath);
        if ("https".equalsIgnoreCase(url.getProtocol())) {
            SSLUtils.ignoreSSL();
        }
        conn = url.openConnection();
        /*sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        String encodedUserPwd = encoder.encode(password.getBytes());
        conn.setRequestProperty("Proxy-Authorization", "Basic "+ encodedUserPwd);
        conn.addRequestProperty("REFERER", "http://cms.pconline.com.cn");*/

//        if("no".equalsIgnoreCase(refererIgnore)){
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; QQDownload 677; Alexa Toolbar; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        /*if (!StringUtils.isBlank(referer)) {
           	conn.setRequestProperty("Referer", referer);
        }*/
        conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        }
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(60000);
        conn.connect();

        InputStream in = new BufferedInputStream(conn.getInputStream());
        OutputStream out = new BufferedOutputStream(new java.io.FileOutputStream(targetFullPath));
        if (in == null) {
            throw new RuntimeException("获得网络资源出错");
        }
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        out.flush();
        out.close();
        in.close();
    }
}
