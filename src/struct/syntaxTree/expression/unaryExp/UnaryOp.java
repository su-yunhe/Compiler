package struct.syntaxTree.expression.unaryExp;

import struct.token.Token;

/**
 * 单目运算符类 UnaryOp
 * 合法的类别只有+, -, !，且!仅能出现在条件表达式中
 * @author SYH
 * @since 2023/09/25
 */
public class UnaryOp {
    private final String name = "<UnaryOp>";
    private Token token;

    public UnaryOp(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(token.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
