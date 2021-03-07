package or.gelivable.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    ##: geli
 */
public class Env {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
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
