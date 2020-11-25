package jav.text;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author
 * @Date 2020/11/25
 * @Version 1.0
 */
public class DateFormatUtil {
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
