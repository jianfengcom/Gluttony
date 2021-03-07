package jav.security;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

public class MD5Util {

    /*
        ##: MD5
     */
    public static String encrypt(String original, String type) throws Exception {
        if ("java".equals(type)) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(original.getBytes("UTF-8"));
            return String.valueOf(encodeHex(bytes));
        }
        if ("sb".equals(type)) {
            return DigestUtils.md5DigestAsHex(original.getBytes());
        }
        return null;
    }

    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];

        for(int i = 0; i < chars.length; i += 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[b >>> 4 & 15];
            chars[i + 1] = HEX_CHARS[b & 15];
        }

        return chars;
    }
}
