package frontend.parser.terminal;

import frontend.lexer.LexType;
import frontend.lexer.Token;

/**
 * 格式字符串终结符
 * @author SYH
 * @since 2023/09/25
 */
public class FormatString {
    /**
     * FormatString 的终结符 token 内容
     */
    private Token token; // STRCON

    /**
     * 构造方法
     * @param token token
     */
    public FormatString(Token token) {
        this.token = token;
    }

    /**
     * 构造方法
     * @param str 格式字符串内容
     * @param lineNum 所在行数
     */
    public FormatString(String str, int lineNum) {
        this.token = new Token(LexType.STRCON, lineNum, str);
    }

    /**
     * 获取内容
     * @return {@link String} 内容
     */
    public String getName() {
        return this.token.getContent();
    }

    /** 获取所在行数
     * @return int 所在行数
     */
    public int getLineNum() {
        return this.token.getLineNum();
    }

}
