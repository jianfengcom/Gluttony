package com.example.util;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/*
    ##: ssl
 */
public class SSLUtils {
    public static void ignoreSSL() throws Exception {
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                System.out.println("Warning: URL Host: " + hostname + " vs. " + session.getPeerHost());
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    public static void ignoreSSL(HttpsURLConnection conn) throws Exception {
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                System.out.println("Warning: URL Host: " + hostname + " vs. " + session.getPeerHost());
                return true;
            }
        };
        trustAllHttpsCertificates();
        conn.setDefaultHostnameVerifier(hv);
    }

    private static void trustAllHttpsCertificates()
            throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new XTrustManager();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class XTrustManager implements TrustManager, X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
        }
    }
}
