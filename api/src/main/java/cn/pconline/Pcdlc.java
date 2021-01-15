package cn.pconline;

/**
 * @Description:
 * @Author
 * @Date 2021/1/15
 * @Version 1.0
 */
public class Pcdlc {

    /*
        key=安卓-360站点 爬取pc页面获取apk下载链接 修改为 爬取wap页面获取apk下载链接

        http://zhushou.360.cn/detail/index/soft_id/4036154
        http://m.app.so.com/detail/&id=4036154
     */
    public static String getWapUrl() {
        String url = "http://zhushou.360.cn/detail/index/soft_id/4036154";
        String wapHost = "http://m.app.so.com";
        int startStr = url.indexOf("/soft_id/");
        String number = url.substring(startStr).replace("/soft_id/", "");
        return wapHost + "/detail/&id=" + number;
    }
}
