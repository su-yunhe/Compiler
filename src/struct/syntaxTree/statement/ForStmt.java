package struct.syntaxTree.statement;

import struct.token.Token;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.primaryExp.LVal;

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
        sb.append(lVal.toString());
        sb.append(eq.toString());
        sb.append(exp.toString());
        sb.append(name).append("\n");
        return sb.toString();
    }

}
