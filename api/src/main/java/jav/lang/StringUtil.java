package jav.lang;

public class StringUtil {

    /**
     * key=分隔字符串
     *
     * @param source
     * @param regex
     */
    public static void split(String source, String regex) {
        if (regex == null) {
            regex = "/";
        }
        String[] split = source.split(regex);
        System.out.println("length=" + split.length);
        for (int i = 0; i < split.length; i++) {
            System.out.println(i + ":" + split[i]);
        }
    }

    /**
     * key=按指定字节长度截取字符串
     * 家居网-品牌库(pchouse-product)
     *
     * @param source
     * @param num
     * @return
     * @Author cjf-pc
     */
    public static String sub(String source, int num) {
        if (source == null || source.getBytes().length <= num) {
            return source;
        }
        return split(source, num - 3) + "...";
    }

    public static String split(String source, int num) { // 1021
        // 记录已经截取的字节
        StringBuffer sb = new StringBuffer();
        // 记录已经截取的字节长度
        int len = 0;

        String temp = null;
        for (int i = 0; i < num && len < num; i++) {
            // 获取单个字符
            temp = String.valueOf(source.charAt(i));
            // 获取单个字符的字节数,累加
            len += temp.getBytes().length;

            if (len > num) {
                break; // 超出的字符不要
            }

            // 拼接到目标字符串上
            sb.append(temp);
        }
        return sb.toString();
    }
}
