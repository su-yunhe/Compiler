import frontend.lexer.LexType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
//        String remainedOfLine = "mainworld 1234 to you hello!";
//        Pattern pattern = Pattern.compile("^main\\W");
//        Matcher matcher = pattern.matcher(remainedOfLine);
//        if (matcher.find()) {
//            System.out.println(matcher.group(0));
//        }
        for (LexType lexType : LexType.values()) {
            System.out.println(lexType.getPattern());
        }
    }
}
