package jav.security;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author
 * @Date 2020/11/11
 * @Version 1.0
 */
public class EncryptionUtil {
    private static final int three_hour_expires = 60 * 60 * 3; // 3小时

    public static void downloadJsp(long masterId, long linkId, int location) {
        String token = buildSimpleToken(masterId, linkId, location);
        unSimpleToken(token);
    }

    /**
     * key=数字型字符串加密
     *
     * ps: 下载中心静态(pconline-dl) 下载中心动态(pconline-dlc2)
     *
     * sql: SELECT dl_link_id FROM dl_link WHERE dl_master_id = 1357799
     *
     * @param masterId
     * @param linkId
     * @param location
     * @return
     * @Author cjf-pc
     */
    public static String buildSimpleToken(long masterId, long linkId, int location) {
        String validTime = String.valueOf((System.currentTimeMillis()) + (three_hour_expires * 1000));
        String masterIdStr = String.valueOf(masterId);
        String linkIdStr = String.valueOf(linkId);
        String str10 = masterIdStr.length() + masterIdStr + linkIdStr.length() + linkIdStr + validTime + location;

        System.out.println("first str10=" + str10);
        return Id2MidUtil.Mid2Uid(str10);
    }

    public static Map<String, Long> unSimpleToken(String token) {
        Map<String, Long> map = new HashMap<String, Long>();

        try {

            String mid = Id2MidUtil.Uid2Mid(token);
            System.out.println("end mid=" + mid);

            int masterIdLength = Integer.parseInt(mid.substring(0, 1));
            long masterId = Long.parseLong(mid.substring(1, masterIdLength + 1));
            map.put("masterId", masterId);

            int linkIdLength = Integer.parseInt(mid.substring(masterIdLength + 1, masterIdLength + 1 + 1));
            long linkId = Long.parseLong(mid.substring(masterIdLength + 1 + 1, masterIdLength + 1 + 1 + linkIdLength));
            map.put("linkId", linkId);

            long timestamp = Long.parseLong(mid.substring(mid.length() - 14, mid.length() - 1)); // 长度13
            System.out.println("timestamp=" + timestamp + ", current=" + System.currentTimeMillis());

            map.put("timestamp", timestamp);

            if ((timestamp - System.currentTimeMillis()) < 0) {
                throw new RuntimeException("密钥超过有效时间");
            }

            long location = Long.parseLong(mid.substring(mid.length() - 1, mid.length()));
            map.put("location", location);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("错误的密钥, token=" + token);
        }

        return map;
    }

    public static class Id2MidUtil {
        private static String[] str62keys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
                "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
                "V", "W", "X", "Y", "Z"};

        // 10进制转成62进制
        public static String IntToEnode62(Integer int10) {
            String str62 = "";
            int r = 0;
            while (int10 != 0) {
                r = int10 % 62;
                str62 = str62keys[r] + str62;
                int10 = (int) Math.floor(int10 / 62.0);
            }
            return str62;
        }

        // 62进制转成10进制
        public static String Str62ToInt(String str62) {
            long int64 = 0;
            for (int i = 0; i < str62.length(); i++) {
                long Vi = (long) Math.pow(62, (str62.length() - i - 1));
                String t = str62.substring(i, i + 1);

                int64 += Vi * findindex(t);
            }
            return Long.toString(int64);
        }

        public static int findindex(String t) {
            int index = 0;
            for (int i = 0; i < str62keys.length; i++) {
                if (str62keys[i].equals(t)) {
                    index = i;
                    break;
                }
            }
            return index;
        }

        public static String Uid2Mid(String mid) {
            String int10 = "";
            for (int i = mid.length() - 4; i > -4; i = i - 4) // 从最后往前以4字节为一组读取URL字符
            {
                int offset = i < 0 ? 0 : i;
                int len = i < 0 ? mid.length() % 4 : 4;

                String str = mid.substring(offset, offset + len);
                System.out.println("解密中: " + offset + " " + len + " " + str);

                String decodeStr = Str62ToInt(str);
                System.out.println("decodeStr=" + decodeStr);

                if (offset > 0) // 若不是第一组，则不足7位补0
                {
                    while (decodeStr.length() < 7) {
                        decodeStr = "0" + decodeStr;
                    }
                }
                int10 = decodeStr + int10;
            }

            return int10;
        }

        public static String Mid2Uid(String str10) {
            String mid = "";
            int count = 1; // 31 24 17 10 3
            for (int i = str10.length() - 7; i > -7; i = i - 7) // 从最后往前以7字节为一组读取字符
            {
                int offset = i < 0 ? 0 : i;
                int len = i < 0 ? str10.length() % 7 : 7;
                String str = str10.substring(offset, offset + len);
                System.out.println("加密中: " + offset + " " + len + " " + str);

                String encodeStr = IntToEnode62(Integer.valueOf(str));
                System.out.println("encodeStr=" + encodeStr);
//				if (count != 3) {// z xghm uXym 生成的链接从右往左的前2组，4位一组，不足4位的补0
                for (int j = 0; j < 4 - encodeStr.length(); j++) {
                    encodeStr = "0" + encodeStr;
                    System.out.println();
                    System.out.println("0url=" + encodeStr + " count=" + count);
                }
//				}
                mid = encodeStr + mid;
                count++;
            }
            return mid;
        }
    }
}
