package jav.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Author
 * @Date 2020/11/25
 * @Version 1.0
 */
public class DateUtil {
    public static final int ONE_DAY = 1;

    /**
     * key=获取指定n天后的时间
     *
     * @param date
     * @param number
     * @return
     */
    public static Date next(Date date, int number) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, number);
        return c.getTime();
    }
}
