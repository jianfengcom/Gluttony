package or.apache.http.client;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
        params.put("ladderId", 1L);
        params.put("pv", "苹果A14");

        // http://local.pconline.com.cn:8082/api/tiantitu/mini
        String url = "http://mall.pconline.com.cn/api/tiantitu/mini";
        String source = HttpClientUtil.post(url, params, DEFAULT_CHARSET);
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
