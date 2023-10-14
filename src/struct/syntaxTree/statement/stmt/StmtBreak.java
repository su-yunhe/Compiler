package struct.syntaxTree.statement.stmt;

import struct.token.Token;

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
        sb.append(breakTk.toString());
        sb.append(semicn.toString());
        return sb.toString();
    }
}
