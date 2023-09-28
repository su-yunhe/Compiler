package frontend.parser.struct.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.LVal;

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
        sb.append(this.lval.toString());
        sb.append(this.eq.toString());
        sb.append(this.exp.toString());
        sb.append(this.semicn.toString());
        return sb.toString();
    }


}
