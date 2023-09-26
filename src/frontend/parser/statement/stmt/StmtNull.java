package frontend.parser.statement.stmt;

import frontend.lexer.Token;

public class StmtNull implements StmtEle{
    private Token semicn;

    public StmtNull(Token semicn) {
        this.semicn = semicn;
    }
}
