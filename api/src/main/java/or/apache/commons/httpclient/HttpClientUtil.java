package or.apache.commons.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jav.util.MapUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author
 * @Date 2020/11/16
 * @Version 1.0
 */
public class HttpClientUtil {

    /**
     * key=HttpClient
     *
     * @param url
     * @param params 请求参数
     * @return
     */
    public static String postMethod(String url, Map<String, String> params) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);

        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);

        // 设置请求参数
        NameValuePair[] pairs = MapUtil.handleParams(params);
        postMethod.setRequestBody(pairs);

        try {
            // 执行POST方法
            int statusCode = httpClient.executeMethod(postMethod);
            System.out.println("statusCode=" + statusCode);
            if (statusCode == 200) {
                return postMethod.getResponseBodyAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * key=HttpClient
     * 电脑网-产品库微信小程序(pconline-pdwx)
     *
     * @param url
     * @return
     * @Author cjf-pc
     */
    public static JSONObject getMethod(String url) {
        JSONObject result = null;

        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);

        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);

        try {
            // 执行GET方法
            int statusCode = httpClient.executeMethod(getMethod);
            System.out.println("statusCode=" + statusCode);
            if (statusCode == 200) {
                String responseStr = getMethod.getResponseBodyAsString();
                result = JSON.parseObject(responseStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
