package frontend.parser.struct.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.struct.expression.Cond;
import frontend.parser.struct.statement.ForStmt;

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
        sb.append(this.forTk.toString());
        sb.append(this.leftParent.toString());
        if (this.forStmt1 != null) {
            sb.append(this.forStmt1.toString());
        }
        sb.append(semiTk1.toString());
        if (this.cond != null) {
            sb.append(this.cond.toString());
        }
        sb.append(semiTk2.toString());
        if (this.forStmt2 != null) {
            sb.append(this.forStmt2.toString());
        }
        sb.append(this.rightParent.toString());
        sb.append(this.stmt.toString());
        return sb.toString();
    }
}
