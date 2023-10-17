package struct.syntaxTree.statement.stmt;

import enums.ReturnType;
import struct.token.Token;
import struct.syntaxTree.expression.Exp;

/**
 * StmtReturn return语句
 * StmtReturn = 'return' [Exp] ';' // 1.有Exp 2.无Exp
 * @author SYH
 * @date 2023/09/26
 */
public class StmtReturn implements StmtEle{
    private Token returnTk; // 'return'
    private Exp exp; // MAY exist
    private Token semicn; // ';'

    public StmtReturn(Token returnTk,
                      Token semicn) {
        this.returnTk = returnTk;
        this.semicn = semicn;
    }

    public StmtReturn(Token returnTk,
                      Exp exp,
                      Token semicn) {
        this(returnTk, semicn);
        this.exp = exp;
    }

    public ReturnType returnType() {
        if (exp != null) {
            return ReturnType.INT;
        } else {
            return ReturnType.VOID;
        }
    }

    public int getLineNum() {
        return returnTk.getLineNum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(returnTk.toString());
        if (exp != null) {
            sb.append(exp.toString());
        }
        sb.append(semicn.toString());
        return sb.toString();
    }

}
