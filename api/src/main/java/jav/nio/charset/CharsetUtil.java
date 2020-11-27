package jav.nio.charset;

import java.nio.charset.Charset;

/**
 * @Description:
 * @Author
 * @Date 2020/11/27
 * @Version 1.0
 */
public class CharsetUtil {
    /**
     * 打印JDK支持的所有字符集
     */
    public static void iterator() {
        for (String s : Charset.availableCharsets().keySet()) {
            System.out.println(s);
        }
    }
}
