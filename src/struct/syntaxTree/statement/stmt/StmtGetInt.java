package struct.syntaxTree.statement.stmt;

import struct.token.Token;
import struct.syntaxTree.expression.primaryExp.LVal;

/**
 * StmtGetInt getint语句
 * LVal '=' 'getint''('')'';'
 * @author SYH
 * @date 2023/09/26
 */
public class StmtGetInt implements StmtEle{
    private LVal lval;
    private Token eq; // '='
    private Token getint; // 'getint'
    private Token leftParent; // '('
    private Token rightParent; // ')'
    private Token semicn; // ';'

    public StmtGetInt(LVal lval,
                      Token eq,
                      Token getint,
                      Token leftParent,
                      Token rightParent,
                      Token semicn) {
        this.lval = lval;
        this.eq = eq;
        this.getint = getint;
        this.leftParent = leftParent;
        this.rightParent = rightParent;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lval.toString());
        sb.append(eq.toString());
        sb.append(getint.toString());
        sb.append(leftParent.toString());
        sb.append(rightParent.toString());
        sb.append(semicn.toString());
        return sb.toString();
    }
}
