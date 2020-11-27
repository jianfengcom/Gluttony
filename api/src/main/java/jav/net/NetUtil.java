package jav.net;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {

    static HostnameVerifier defaultHostnameVerifier = new HostnameVerifier() {
        @Override
        public boolean verify(String host, SSLSession session) {
            return true;
        }
    };

    /**
     * key=IP地址是否可达
     *
     * @param host 指定的主机
     * @return
     */
    public static boolean isReachable(String host) { // IP地址是否可达，相当于ping命令
        boolean reachable = false;
        try {
            InetAddress address = InetAddress.getByName(host);
            reachable = address.isReachable(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reachable;
    }

    /**
     * key=网址是否可达
     * todo: 重定向未处理
     *
     * @param address 网址
     * @return
     */
    public static boolean connectingAddress(String address) {
        boolean flag = false;
        String tempUrl = address.substring(0, 5); // 取出地址前5位
        if (tempUrl.contains("http")) {
            if (tempUrl.equals("https")) { // 当协议是https时
                try {
                    trustAllHttpsCertificates();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                HttpsURLConnection.setDefaultHostnameVerifier(defaultHostnameVerifier);
            }
            flag = isConnectUp(address);
        }
        return flag;
    }

    public static boolean isConnectUp(String address) {
        boolean connFlag = false;
        URL url;
        HttpURLConnection conn = null;

        try {
            url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3 * 1000);
            if (conn.getResponseCode() == 200) { // 如果连接成功则设置为true
                connFlag = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return connFlag;
    }

    /*以下是Https适用*/
    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager trustManager = new LocalTrustManager();
        trustAllCerts[0] = trustManager;
        javax.net.ssl.SSLContext sslContext = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sslContext.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
                .getSocketFactory());
    }

    static class LocalTrustManager implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
    /*以上是Https适用*/
}