package frontend.parser.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.expression.Exp;

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
}
