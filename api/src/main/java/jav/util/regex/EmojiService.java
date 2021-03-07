package jav.util.regex;

public class EmojiService {
    public static void main(String[] args) {
        String s = EmojiUtils.emojiConvert("\uD83D\uDE1A\uD83D\uDE1C\uD83D\uDE1F" + "大狗");
        System.out.println("s=" + s);

        String e = EmojiUtils.emojiRecovery(s);
        System.out.println("e=" + e);
        System.out.println("\uD83D\uDE1A\uD83D\uDE1C\uD83D\uDE1F");
        //System.out.println(😚😜😟);
    }
}
