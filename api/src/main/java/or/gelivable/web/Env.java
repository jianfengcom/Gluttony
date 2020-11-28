package or.gelivable.web;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author
 * @Date 2020/11/28
 * @Version 1.0
 */
public class Env {
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String param(String name, String def) {
        String v = this.request.getParameter(name);
        return v == null ? def : v;
    }

    public int paramInt(String name, int def) {
        String v = this.request.getParameter(name);
        if (v != null && v.length() != 0) {
            try {
                return Integer.parseInt(v);
            } catch (Exception e) {
                e.printStackTrace();
                return def;
            }
        } else {
            return def;
        }
    }
}
