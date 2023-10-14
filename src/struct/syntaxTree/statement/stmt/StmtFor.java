package struct.syntaxTree.statement.stmt;

import struct.token.Token;
import struct.syntaxTree.expression.Cond;
import struct.syntaxTree.statement.ForStmt;

/**
 * StmtFor for语句
 * StmtFor = 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
 * @author SYH
 * @date 2023/09/26
 */
public class StmtFor implements StmtEle{
    private Token forTk; // for
    private Token leftParent; // '('
    private ForStmt forStmt1 = null; // may exist
    private Token semiTk1; // ';'
    private Cond cond = null; // may exist
    private Token semiTk2; // ';'
    private ForStmt forStmt2 = null; // may exist
    private Token rightParent; // ')'
    private Stmt stmt;

    public StmtFor(Token forTk, Token leftParent, ForStmt forStmt1, Token semiTk1,
                   Cond cond, Token semiTk2, ForStmt forStmt2, Token rightParent, Stmt stmt
                   ) {
        this.forTk = forTk;
        this.leftParent = leftParent;
        this.forStmt1 = forStmt1;
        this.semiTk1 = semiTk1;
        this.cond = cond;
        this.semiTk2 = semiTk2;
        this.forStmt2 = forStmt2;
        this.rightParent = rightParent;
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(forTk.toString());
        sb.append(leftParent.toString());
        if (forStmt1 != null) {
            sb.append(forStmt1.toString());
        }
        sb.append(semiTk1.toString());
        if (cond != null) {
            sb.append(cond.toString());
        }
        sb.append(semiTk2.toString());
        if (forStmt2 != null) {
            sb.append(forStmt2.toString());
        }
        sb.append(rightParent.toString());
        sb.append(stmt.toString());
        return sb.toString();
    }
}
