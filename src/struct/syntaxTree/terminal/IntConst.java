package struct.syntaxTree.terminal;

import eumes.LexType;
import struct.token.Token;

/**
 * 数值常量终结符
 * @author SYH
 * @since 2023/09/25
 */
public class IntConst {
    /**
     * IntConst 的终结符 token 内容
     */
    private Token token; // INTCON

    /**
     * 构造方法
     * @param token token
     */
    public IntConst(Token token) {
        this.token = token;
    }

    /**
     * 构造方法
     * @param numStr 数值内容
     * @param lineNum 所在行数
     */
    public IntConst(String numStr, int lineNum) {
        this.token = new Token(LexType.INTCON, lineNum, numStr);
    }

    /**
     * 获取数值
     * @return int 数值
     */
    public int getNum() {
        return Integer.valueOf(token.getContent());
    }

    /**
     * 获取所在行数
     * @return int 所在行数
     */
    public int getLineNum() {
        return token.getLineNum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(token.toString());
        return sb.toString();
    }
}
