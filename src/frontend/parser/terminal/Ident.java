package frontend.parser.terminal;

import frontend.lexer.LexType;
import frontend.lexer.Token;

/**
 * 标识符 Identifier
 * @author SYH
 * @since 2023/09/25
 */
public class Ident {
    /**
     * Ident 的终结符 token 内容
     */
    private Token token;

    /**
     * 构造方法
     * @param token Ident 的终结符 token 内容
     */
    public Ident(Token token) {
        this.token = token;
    }

    /**
     * 构造方法【LexType被固定为IDENFR】
     * @param name ident 内容
     * @param lineNum 所在行数
     */
    public Ident(String name, int lineNum) {
        this.token = new Token(LexType.IDENFR, lineNum, name);
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
