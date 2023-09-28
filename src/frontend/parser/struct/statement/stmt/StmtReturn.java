package frontend.parser.struct.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.struct.expression.Exp;

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
}
