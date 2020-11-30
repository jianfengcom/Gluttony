package javx.servlet.http;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Description:
 * @Author
 * @Date 2020/11/30
 * @Version 1.0
 */
public class XssHttpServletRequest extends HttpServletRequestWrapper {
    public HttpServletRequest request;

    // 需要重写构造方法
    public XssHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    // 需要重写getParameter(name)方法,将value进行转义
    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        System.out.println("转义之前: value="+value);
        if(StringUtils.isNotBlank(value)){
            // 转化为html, <script>标签都会转化为html格式  &lt;script&gt;
            value = StringEscapeUtils.escapeHtml4(value);
        }
        System.out.println("转义之后: value="+value);
        return value;
    }
}
