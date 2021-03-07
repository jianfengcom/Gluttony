package or.apache.http.client;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * @Description 推荐 org.apache.httpcomponents » httpclient  CloseableHttpClient
 * @Author
 * @Date 2020/11/27
 * @Version 1.0
 */
public class HttpClientUtil {

    /*
        请求超时时间
        这个时间定义了socket读数据的超时时间，也就是连接到服务器之后到从服务器获取响应数据需要等待的时间
        发生超时，会抛出SocketTimeoutException异常
     */
    private static final int SOCKET_TIME_OUT = 60000;
    /*
        连接超时时间
        这个时间定义了通过网络与服务器建立连接的超时时间，也就是取得了连接池中的某个连接之后到接通目标url的连接等待时间
        发生超时，会抛出ConnectionTimeoutException异常
     */
    private static final int CONNECT_TIME_OUT = 60000;

    /**
     * ##: get
     *
     * @param url
     * @param charset "UTF-8"
     * @Description
     * @Author
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static String get(String url, String charset) {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        String result = null;
        try {
            // 使用HttpClient发起请求，获取response
            response = httpClient.execute(httpGet);

            // 解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * ##: post
     *
     * @param url
     * @param charset "UTF-8"
     * @Description
     * @Author
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static String post(String url, Map<String, Object> params, String charset) {
        // Content-type

        // 创建HttpClient对象
        CloseableHttpClient httpClient;
        HttpPost method;
        CloseableHttpResponse response;
        HttpEntity entity = null;
        String result = null;

        try {
            httpClient = HttpClients.createDefault();
            /*
                建立Request的对象
                Request一般配置addHeader、setEntity、setConfig
             */
            method = new HttpPost(url);

            // 设置配置
            RequestConfig reqConfig = RequestConfig.custom()
                    .setSocketTimeout(SOCKET_TIME_OUT) // 设置请求超时时间
                    .setConnectTimeout(CONNECT_TIME_OUT) // 设置连接超时时间
                    .build();
            method.setConfig(reqConfig);

            // 设置请求参数
            entity = new UrlEncodedFormEntity(HttpClientUtilHelp.createParam(params), Consts.UTF_8);
            method.setEntity(entity);

            response = httpClient.execute(method);
            entity = response.getEntity();

            /*
                用EntityUtils.toString()这个静态方法将HttpEntity转换成字符串,
                为防止服务器返回的数据带有中文,可以在转换的时候将字符集指定成utf-8
             */
            result = EntityUtils.toString(entity, charset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 一定要记得把entity fully consume掉，否则连接池中的connection就会一直处于占用状态
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
