package jav.util;

import org.apache.commons.httpclient.NameValuePair;

import java.util.Map;

/**
 * @Description:
 * @Author
 * @Date 2020/11/16
 * @Version 1.0
 */
public class MapUtil {

    /**
     *
     * @param params 请求参数
     * @return
     */
    public static NameValuePair[] handleParams(Map<String,String> params) {
        NameValuePair[] pairs = new NameValuePair[params.size()];
        int index = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            pairs[index++] = new NameValuePair(entry.getKey(), entry.getValue());
        }
        return pairs;
    }
}
