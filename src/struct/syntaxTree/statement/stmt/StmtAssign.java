package struct.syntaxTree.statement.stmt;

import struct.token.Token;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.primaryExp.LVal;

/**
 * StmtAssign 赋值语句类
 * StmtAssign = LVal '=' Exp ';'
 * @author SYH
 * @date 2023/09/26
 */
public class StmtAssign implements StmtEle {
    private LVal lval;
    private Token eq; // '='
    private Exp exp;
    private Token semicn; // ';'

    public StmtAssign(LVal lval, Token eq, Exp exp, Token semicn) {
        this.lval = lval;
        this.eq = eq;
        this.exp = exp;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lval.toString());
        sb.append(eq.toString());
        sb.append(exp.toString());
        sb.append(semicn.toString());
        return sb.toString();
    }


}
