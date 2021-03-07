package or.apache.commons.httpclient;

import jav.util.MapUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.*;
import java.util.Map;

/**
 * @Description 不推荐 commons-httpclient » commons-httpclient  HttpClient
 * @Author
 * @Date 2020/11/27
 * @Version 1.0
 */
public class HttpClientUtil {

    /**
     * ##: post
     *
     * @param url
     * @Description
     * @Author
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static String postMethod(String url, Map<String, Object> params) {
        HttpClient httpClient;
        PostMethod postMethod = null;
        InputStream stream = null;
        BufferedReader reader = null;

        try {
            httpClient = new HttpClient();

            // 连接超时: 指的是连接一个url的连接等待时间
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            // 读取数据超时: 指的是连接上一个url，获取response的返回等待时间
            // 源码追溯: DefaultHttpParams 62行
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(3000);

            postMethod = new PostMethod(url);
            // 源码追溯: DefaultHttpParams 62行
            // postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);

            // 设置请求参数
            NameValuePair[] pairs = MapUtil.handleParams(params);
            postMethod.setRequestBody(pairs);

            // 默认请求编码为ISO-8859-1, 可通过设置Content-Type更改请求编码
            System.out.println("默认请求编码=" + postMethod.getRequestCharSet());
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            // 执行POST方法
            int statusCode = httpClient.executeMethod(postMethod);

            // 意味着你的请求发送到服务器花的时间 比服务器准备等待的时间长。 换句话说，连接已超时。
            // 重发
            int time = 0;
            while (HttpStatus.SC_REQUEST_TIMEOUT == statusCode && time++ < 3) {
                statusCode = httpClient.executeMethod(postMethod);
            }

            if (statusCode == 200) {
                // 直接返回字符串
                // return postMethod.getResponseBodyAsString();

                // UTF-8
                stream = postMethod.getResponseBodyAsStream();
                reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * ##: get
     * ##: wait 代理
     *
     * @param url
     * @Description
     * @Author cjf-pc
     * @Date
     * @From 电脑网-产品库微信小程序(pconline-pdwx)
     * @Function BaiduService.get()
     * @Version 1.0
     */
    public static String getMethod(String url) {
        HttpClient httpClient;
        GetMethod getMethod = null;

        try {
            httpClient = new HttpClient();

            // 连接超时
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            // 读取数据超时
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(3000);

            getMethod = new GetMethod(url);

            // 执行GET方法
            int statusCode = httpClient.executeMethod(getMethod);

            int time = 0;
            while (HttpStatus.SC_REQUEST_TIMEOUT == statusCode && time++ < 3) {
                statusCode = httpClient.executeMethod(getMethod);
            }

            if (statusCode == 200) {
                return getMethod.getResponseBodyAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
            }
        }
        return null;
    }

}
