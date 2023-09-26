package frontend.parser.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.expression.Cond;
import frontend.parser.statement.ForStmt;

public class StmtFor implements StmtEle{
    private Token forTk; // for
    private Token leftParent; // '('
    private ForStmt forStmt1 = null; // may exist
    private Token semiTk1; //;
    private Cond cond = null; // may exist
    private Token semiTk2; //;
    private ForStmt forStmt2 = null; // may exist
    private Token rightParent; // )
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


}
