package or.apache.http.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String DEFAULT_CHARSET = "UTF-8";

    public static void main(String[] args) {
        post();
    }

    public static void post() {
        List<Vo> list = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        String pv = "苹果A14";
        try {
            pv = URLDecoder.decode(pv, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        params.put("ladderId", 1L);
        params.put("pv", pv);

        // http://local.pconline.com.cn:8082/api/tiantitu/mini
        String url = "http://mall.pconline.com.cn/api/tiantitu/mini";
        String source = HttpClientUtil.post(url, params, "UTF-8");
        JSONObject target = HttpClientUtilHelp.toJson(source);

        JSONArray data = target.getJSONArray("data");
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                Vo vo = new Vo();
                vo.setId(data.getJSONObject(i).getLong("productId"));
                vo.setName(data.getJSONObject(i).getString("productName"));
                list.add(vo);
            }
        }

        //======== start ========
        System.out.println(list);
        //======== end ========


        //======== start ========
        JSONObject newJsonObject = new JSONObject();
        newJsonObject.put("vos", list);
        System.out.println(newJsonObject.toJSONString());
        //======== end ========
    }
}
