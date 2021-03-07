package jav.util;

import org.apache.commons.httpclient.NameValuePair;

import java.util.Map;

public class MapUtil {

    public static NameValuePair[] handleParams(Map<String, Object> params) {
        NameValuePair[] pairs = new NameValuePair[params.size()];
        int index = 0;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            pairs[index++] = new NameValuePair(entry.getKey(), entry.getValue().toString());
        }
        return pairs;
    }

}
