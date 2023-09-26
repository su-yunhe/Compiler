package frontend.parser.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.expression.Cond;

public class StmtCond implements StmtEle{
    private Token ifTk; // 'if'
    private Token leftParent; // '('
    private Cond cond;
    private Token rightParent; // ')'
    private Stmt ifStmt;
    private Token elseTk; // 'else' MAY exist
    private Stmt elseStmt; // MAY exist

    public StmtCond(Token ifTk,
                    Token leftParent,
                    Cond cond,
                    Token rightParent,
                    Stmt ifStmt) {
        this.ifTk = ifTk;
        this.ifStmt = ifStmt;
        this.leftParent = leftParent;
        this.cond = cond;
        this.rightParent = rightParent;
    }
}
