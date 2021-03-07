package jav.util.regex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiUtils {

    private static final Logger LOG = LoggerFactory.getLogger(EmojiUtils.class);

    private static final String ENCODING = "UTF-8";

    private EmojiUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String emojiConvert(String str) {
        String patternString = "([\ud800-\udbff\udc00-\udfff])";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb, "[[" + URLEncoder.encode(matcher.group(1), ENCODING) + "]]");
            } catch (UnsupportedEncodingException e) {
                LOG.error("emoji convert fail", e);
                return str;
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String emojiRecovery(String str) {
        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb, URLDecoder.decode(matcher.group(1), ENCODING));
            } catch (UnsupportedEncodingException e) {
                LOG.error("emoji recovery fail", e);
                return str;
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}