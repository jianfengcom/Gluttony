package javx.servlet.filter;

import or.gelivable.web.Env;
import or.gelivable.web.EnvUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author
 * @Date 2020/11/30
 * @Version 1.0
 */

// todo: 与XssFilter不兼容
// @WebFilter(filterName = "envFilter", urlPatterns = "/*")
// @Order(2)
public class EnvFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Env env = EnvUtils.getEnv();

        try {
            env.setRequest((HttpServletRequest)request);
            env.setResponse((HttpServletResponse)response);
            request.setAttribute("env", env);
            chain.doFilter(request, response);
        } finally {
            EnvUtils.removeEnv();
        }
    }

    @Override
    public void destroy() {

    }
}
