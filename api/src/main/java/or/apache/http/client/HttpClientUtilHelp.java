package or.apache.http.client;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtilHelp {
    public static List<NameValuePair> createParam(Map<String, Object> params) {
        // 建立一个NameValuePair数组，用于存储请求参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null) {
            for (String key : params.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
        }
        return nameValuePairs;
    }

    public static JSONObject toJson(String source) {
        if (source == null) {
            return null;
        }

        return JSONObject.parseObject(source);
    }
}
