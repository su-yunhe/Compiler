package frontend.parser.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.expression.primaryExp.LVal;

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
}
