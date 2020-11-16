package or.apache.commons.httpclient;

import com.alibaba.fastjson.JSON;
import jav.util.MapUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * @param url
     * @param params
     * @return
     */
    public static String postMethod(String url, Map<String, String> params) {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);

        PostMethod postMethod = new PostMethod(url);
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
}
