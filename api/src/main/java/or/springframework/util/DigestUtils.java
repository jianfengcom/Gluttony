package or.springframework.util;

/**
 * @Description: key=MD5
 * @Author
 * @Date 2021/1/15
 * @Version 1.0
 */
public class DigestUtils {
    public static String encrypt() {
        return org.springframework.util.DigestUtils.md5DigestAsHex("Shanghai".getBytes());
    }
}
