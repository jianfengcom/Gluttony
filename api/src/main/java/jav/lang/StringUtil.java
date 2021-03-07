package jav.lang;

public class StringUtil {

    /**
     * ##: 分隔字符串
     *
     * @param source
     * @param separator 分隔符
     * @Description
     * @Author chenjianfeng1
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static void split(String source, String separator) {
        if (separator == null) {
            separator = "/";
        }
        String[] split = source.split(separator);
        System.out.println("length=" + split.length);
        for (int i = 0; i < split.length; i++) {
            System.out.println(i + ":" + split[i]);
        }
    }

    /**
     * ##: 截取指定字节长度的字符串
     *
     * @param byteSize 字节长度
     * @Description
     * @Author chenjianfeng1
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static String substring(String source, int byteSize) {
        // 记录已经截取的字节
        StringBuffer sb = new StringBuffer();
        // 记录已经截取的字节长度
        int len = 0;

        String temp = null;
        for (int i = 0; i < byteSize && len < byteSize; i++) {
            // 获取单个字符
            temp = String.valueOf(source.charAt(i));
            // 获取单个字符的字节数,累加
            len += temp.getBytes().length;

            if (len > byteSize) {
                break; // 超出的字符不要
            }

            // 拼接到目标字符串上
            sb.append(temp);
        }
        return sb.toString();
    }
}
