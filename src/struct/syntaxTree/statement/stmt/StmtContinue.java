package struct.syntaxTree.statement.stmt;

import struct.token.Token;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(continueTk.toString());
        sb.append(semicn.toString());
        return sb.toString();
    }
}
