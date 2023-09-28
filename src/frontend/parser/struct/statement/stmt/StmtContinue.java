package frontend.parser.struct.statement.stmt;

import frontend.lexer.Token;

/**
 * StmtContinue continue语句
 * StmtContinue = 'continue' ';'
 * @author SYH
 * @date 2023/09/26
 */
public class StmtContinue implements StmtEle{
    private Token continueTk; // 'continue'
    private Token semicn; // ';'

    public StmtContinue(Token continueTk, Token semicn) {
        this.continueTk = continueTk;
        this.semicn = semicn;
    }
}
