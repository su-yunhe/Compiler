package frontend.parser.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.expression.Exp;

public class StmtExp implements StmtEle{
    private Exp exp;
    private Token semicn; // ';'

    public StmtExp(Exp exp, Token semicn) {
        this.exp = exp;
        this.semicn = semicn;
    }
}
