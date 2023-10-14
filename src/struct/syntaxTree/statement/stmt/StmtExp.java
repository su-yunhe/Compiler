package struct.syntaxTree.statement.stmt;

import struct.token.Token;
import struct.syntaxTree.expression.Exp;

/**
 * StmtExp 表达式语句类
 * StmtExp = [Exp] ';'
 * @author SYH
 * @date 2023/09/26
 */
public class StmtExp implements StmtEle{
    private Exp exp;
    private Token semicn; // ';'

    public StmtExp(Exp exp, Token semicn) {
        this.exp = exp;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(exp.toString());
        sb.append(semicn.toString());
        return sb.toString();
    }
}
