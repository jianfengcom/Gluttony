package jav.util.regex;

import java.util.regex.Pattern;

/**
 * @Description:
 * @Author
 * @Date 2020/11/11
 * @Version 1.0
 */
public class PatternDemo {

    public static void main(String[] args) {
        String source = " A d 10 2.48 官方完整版 ";
        String target = hanziReplace(source);
        System.out.println(target);
    }

    /**
     * key=替换汉字
     * @param source
     * @return
     */
    public static String hanziReplace(String source) {
        String target = Pattern.compile("[\\u4e00-\\u9fa5]+")
                .matcher(source).replaceAll(""); // 替换汉字
        target = Pattern.compile("[a-z|A-Z]+")
                .matcher(target).replaceAll(""); // 替换英文字母

        // 等价于
        /*String target = source.replaceAll("[\\u4e00-\\u9fa5]+", "")
                .replaceAll("[a-z|A-Z]+", "");*/

        target = target.trim();
        return target.replaceAll(" ", "_");
    }
}
