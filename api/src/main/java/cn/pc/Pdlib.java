package cn.pc;

public class Pdlib {

    /**
     * ##: 过滤空行
     *
     * @Description
     * @Author
     * @Date
     * @From
     * @Function ProductItemWEB.java
     * @Version 1.0
     */
    public static String filterBlankLine(String target) {
        return target.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
    }
}
