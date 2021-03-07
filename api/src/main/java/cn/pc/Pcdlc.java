package cn.pc;

public class Pcdlc {

    /**
     * ##: 360站点的软件终端页的下载链接
     *
     * @Description 从360的软件终端页-pc获取apk下载链接 修改为 wap
     * @Author chenjianfeng1
     * @Date 2021/1/15
     * @From
     * @Function AssistantFor360AndroidSpiderTask.java
     * @Version 1.0
     */
    public static String getWapUrl() {
        /*
            爬虫 - 爬取安卓软件终端页
            源站点: 360手机助手
         */
        String url = "http://zhushou.360.cn/detail/index/soft_id/4036154";
        String wapHost = "http://m.app.so.com";
        int startStr = url.indexOf("/soft_id/");
        String number = url.substring(startStr).replace("/soft_id/", "");
        return wapHost + "/detail/&id=" + number;
    }
}
