package frontend.lexer;

import java.util.ArrayList;

/**
 * Token列表类
 * @author SYH
 * @since 2023/09/18
 */
public class TokenList {
    /**
     * Token列表
     */
    private ArrayList<Token> tokens;

    /**
     * 构造函数：初始化一个Token的ArrayList
     */
    public TokenList() {
        this.tokens = new ArrayList<>();
    }

    /**
     * 添加 Token 至 Token列表
     * @param token 要加入的Token
     */
    public void addToken(Token token) {
        this.tokens.add(token);
    }

    /**
     * 获取当前的 Token列表
     * @return ArrayList of Token Token列表
     */
    public ArrayList<Token> getTokenList() {
        return this.tokens;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append(token.getLexType()).append(" ").append(token.getContent()).append("\n");
        }
        return sb.toString();
    }

}
