package jav.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final int NEST = 1;

    /**
     * ##: 获取指定n天后的时间
     *
     * @param number 可以为负数
     * @Description
     * @Author chenjianfeng1
     * @Date
     * @From
     * @Function
     * @Version 1.0
     */
    public static Date next(Date date, int number) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, number);
        return c.getTime();
    }

}
