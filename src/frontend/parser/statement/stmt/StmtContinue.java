package frontend.parser.statement.stmt;

import frontend.lexer.Token;

public class StmtContinue implements StmtEle{
    private Token continueTk; // 'continue'
    private Token semicn; // ';'

    public StmtContinue(Token continueTk, Token semicn) {
        this.continueTk = continueTk;
        this.semicn = semicn;
    }
}
