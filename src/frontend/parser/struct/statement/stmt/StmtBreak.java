package frontend.parser.struct.statement.stmt;

import frontend.lexer.Token;

/**
 * StmtBreak break语句
 * StmtBreak = 'break' ';'
 * @author SYH
 * @date 2023/09/26
 */
public class StmtBreak implements StmtEle{
    private Token breakTk; // 'break'
    private Token semicn; // ';'

    public StmtBreak(Token breakTk,
                     Token semicn) {
        this.breakTk = breakTk;
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.breakTk.toString());
        sb.append(this.semicn.toString());
        return sb.toString();
    }
}
