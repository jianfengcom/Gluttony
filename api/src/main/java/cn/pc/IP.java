package cn.pc;

import javax.servlet.http.HttpServletRequest;

public class IP {

    /**
     * ##: IP
     *
     * @param request 请求对象
     * @Description: 获取客户端的IP
     * @Author
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP-X-FORWARDED-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        } else if (ip.length() > 15) {
            String targetIp = getTargetIp(ip);
            if (targetIp != null)
                return targetIp;
        }
        return ip;
    }

    public static String getTargetIp(String source) {
        if (source == null) {
            source = "unknown, 120.240.100.33, 192.168.238.25, 192.168.238.153";
        }
        String[] ipList = source.split(",");
        for(int index = 0; index < ipList.length; index ++){
            String ip = ipList[index].trim();
            if(!("unknown".equalsIgnoreCase(ip))){
                return ip;
            }
        }
        return null;
    }
}
