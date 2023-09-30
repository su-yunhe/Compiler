package frontend.lexer;

import java.util.ArrayList;

/**
 * Token列表
 *
 * @author SYH
 * @date 2023/09/28
 * @since 2023/09/18
 */
public class TokenList {
    /**
     * Token列表
     */
    private static ArrayList<Token> tokens = new ArrayList<>();


    /**
     * 私有化构造函数：初始化一个Token的ArrayList
     */
    private TokenList() {
//        tokens = new ArrayList<>();
//        instance = new TokenList();
    }


    /**
     * 添加 Token 至 Token列表
     * @param token 要加入的Token
     */
    public static void addToken(Token token) {
        tokens.add(token);
    }

    /**
     * 获取当前的 Token列表
     * @return ArrayList of Token Token列表
     */
    public static ArrayList<Token> getTokenList() {
        return tokens;
    }



    public static String TokenList2String() {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append(token.getLexType()).append(" ").append(token.getContent()).append("\n");
        }
        return sb.toString();
    }
}
