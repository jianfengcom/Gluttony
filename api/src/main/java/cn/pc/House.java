package cn.pc;

import jav.lang.StringUtil;

public class House {

    /**
     * ##: 截取指定字节长度的字符串
     *
     * @Description
     * @Author chenjianfeng1
     * @Date
     * @From 家居网-品牌库(pchouse-product)
     * @Function
     * @Version 1.0
     */
    public static String sub(String source, int num) {
        if (source == null || source.getBytes().length <= num) {
            return source;
        }
        return StringUtil.substring(source, num - 3) + "...";
    }

}
