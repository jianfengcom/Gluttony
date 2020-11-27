package or.apache.http.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Description:
 * @Author
 * @Date 2020/11/27
 * @Version 1.0
 */
public class HttpClientUtil {

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
}
