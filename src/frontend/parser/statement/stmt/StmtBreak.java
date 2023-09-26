package frontend.parser.statement.stmt;

import frontend.lexer.Token;

public class StmtBreak implements StmtEle{
    private Token breakTk; // 'break'
    private Token semicn; // ';'

    public StmtBreak(Token breakTk,
                     Token semicn) {
        this.breakTk = breakTk;
        this.semicn = semicn;
    }
}
