package javx.servlet.filter;

import javx.servlet.http.XssHttpServletRequest;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description:
 * @Author
 * @Date 2020/11/30
 * @Version 1.0
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
@Order(1)
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 强转为HttpServletRequest
        HttpServletRequest req = (HttpServletRequest)request;
        // 需要重写request, 重建一个类XssHttpServletRequest 继承 HttpServletRequestWrapper, 重写构造和getParameter方法
        XssHttpServletRequest xssHttpServletRequest = new XssHttpServletRequest(req);
        // 务必传入是重写过的request, 放行
        chain.doFilter(xssHttpServletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
