package or.apache.http.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
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
     * key=CloseableHttpClient
     *
     * @param uri
     * @param defaultCharset
     */
    public static void get(String uri, String defaultCharset) {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet(uri);

        CloseableHttpResponse response = null;
        try {
            // 使用HttpClient发起请求，获取response
            response = httpClient.execute(httpGet);

            //解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), defaultCharset);
                System.out.println(content);
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
    }

    /**
     * key=CloseableHttpClient
     *
     * @param url
     * @param params
     * @param defaultCharset
     * @return
     */
    public static String post(String url, Map<String, Object> params, String defaultCharset) {
        // 创建HttpClient对象
        CloseableHttpClient httpClient;
        CloseableHttpResponse response;
        HttpEntity entity;

        try {
            httpClient = HttpClients.createDefault();
            /*
                建立Request的对象
                Request一般配置addHeader、setEntity、setConfig
             */
            HttpPost method = new HttpPost(url);

            method.addHeader("Content-type","application/json; charset=utf-8");
            method.setHeader("Accept", "application/json");

            // setConfig,添加配置,如设置请求超时时间,连接超时时间
            RequestConfig reqConfig = RequestConfig.custom()
                    .setSocketTimeout(SOCKET_TIME_OUT)
                    .setConnectTimeout(CONNECT_TIME_OUT).build();
            method.setConfig(reqConfig);

            // setEntity 设置请求参数
            entity = new UrlEncodedFormEntity(createParam(params), Consts.UTF_8);
            method.setEntity(entity);

            // 执行Request请求
            response = httpClient.execute(method);
            entity = response.getEntity();

            /*
                用EntityUtils.toString()这个静态方法将HttpEntity转换成字符串,
                为防止服务器返回的数据带有中文,可以在转换的时候将字符集指定成utf-8
             */
            return EntityUtils.toString(entity, defaultCharset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 一定要记得把entity fully consume掉，否则连接池中的connection就会一直处于占用状态
//            EntityUtils.consume(entity);
        }
        return null;
    }

    private static List<NameValuePair> createParam(Map<String, Object> params) {
        // 建立一个NameValuePair数组，用于存储请求参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null) {
            for (String key : params.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
        }
        return nameValuePairs;
    }

    public static void main(String[] args) {
        List<LadderProductVo> list = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("ladderId", 1L);
        params.put("pv", "苹果A14");
        // params.put("callback", "callback");
//        String url = "http://local.pconline.com.cn:8082/api/tiantitu/mini";
        String url = "http://mall.pconline.com.cn/api/tiantitu/mini";
        String ret = post(url, params, "UTF-8");
        System.out.println("ret=" + ret);
        JSONObject jsonObject = JSONObject.parseObject(ret);
        JSONArray data = jsonObject.getJSONArray("data");
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                LadderProductVo vo = new LadderProductVo();
                vo.setId(data.getJSONObject(i).getLong("productId"));
                vo.setName(data.getJSONObject(i).getString("productName"));
                list.add(vo);
            }
        }

        System.out.println(list);
    }
}
