package frontend.parser.struct.statement;

import frontend.lexer.Token;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.LVal;

/** ForStmt 语句
 * 语句 ForStmt → LVal '=' Exp // 存在即可
 * @author SYH
 * @date 2023/09/26
 */
public class ForStmt {
    private final String name = "<ForStmt>";
    private LVal lVal;
    private Token eq; // =
    private Exp exp;

    public ForStmt(LVal lVal, Token eq, Exp exp) {
        this.lVal = lVal;
        this.eq = eq;
        this.exp = exp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.lVal.toString());
        sb.append(this.eq.toString());
        sb.append(this.exp.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }

}
