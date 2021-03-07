<%@ page import="or.gelivable.web.EnvUtils" %>
<%@ page import="or.gelivable.web.Env" %>
<%@ page import="java.net.InetAddress" %>
<%@ page import="org.springframework.util.DigestUtils" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="or.apache.commons.httpclient.HttpClientUtil" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@page language="java" contentType="text/html; charset=utf-8" %>
<%@page session="false" %>

<%
    Env env = EnvUtils.getEnv();
    env.setRequest(request);
    String packageName = env.param("packageName", "");
    System.out.println("packageName=" + packageName);
    String ipAddr = HttpClientUtil.getIpAddr(request);
    System.out.println("ipAddr=" + ipAddr);
%>

<%
    if (StringUtils.isNotBlank(packageName)) {
        InetAddress addr = InetAddress.getLocalHost();
        String address = addr.getHostAddress();

        String sign = DigestUtils.md5DigestAsHex(("d4c0f47c4525cf22f1947860f294be88" + packageName).getBytes());

        // http://111.230.135.96/tpy_admin/gateway/api/getApkInfo?pn=com.tencent.tmgp.pubgmhd&sign=6f19221ed2ce1a29ffbf7eb472f86506&ip=127.0.0.1
        String downLoadAddr = "http://111.230.135.96/tpy_admin/gateway/api/getApkInfo?";
        downLoadAddr += "pn=" + packageName;
        downLoadAddr += "&sign=" + sign;
        downLoadAddr += "&ip=" + address;

        System.out.println("downLoadAddr=" + downLoadAddr);

        String ret = HttpClientUtil.postMethod(downLoadAddr,new HashMap<>());
        if (StringUtils.isNotBlank(ret)) {
            JSONObject jsonObject = JSON.parseObject(ret);
            String downloadlink = jsonObject.getString("downloadlink");
            if (StringUtils.isNotBlank(downloadlink)) {
				/*Date date = new Date(System.currentTimeMillis()+10*60*1000); // 10分钟
				cacheClient.set(cacheKey, downloadlink, date);*/

                response.sendRedirect(downloadlink);
                return;
            }
        }
    }
%>
