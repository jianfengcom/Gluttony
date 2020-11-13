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


    /**
     * key=过滤<img>
     * <img src="http://img.pconline.com.cn/images/upload/upc/tx/pc_best/2011/13/c89/240380649_1605244147.jpg">
     * 电脑网-今日聚超值(pconline-best)
     *
     * @param content
     * @return
     */
    public static String filterImg(String content) {
        Pattern pattern = Pattern.compile("<img[^>]*[0-9]+_[0-9]{10}\\.jpg[^>]*/?>");
        return pattern.matcher(content).replaceAll("");
    }
}
