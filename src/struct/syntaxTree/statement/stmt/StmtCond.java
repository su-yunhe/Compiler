package struct.syntaxTree.statement.stmt;

import struct.token.Token;
import struct.syntaxTree.expression.Cond;

/**
 * StmtCond if语句项
 * StmtCond = 'if' '(' Cond ')' Stmt [ 'else' Stmt ] // 1.有else 2.无else
 * @author SYH
 * @date 2023/09/26
 */
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

    public StmtCond(Token ifTk,
                    Token leftParent,
                    Cond cond,
                    Token rightParent,
                    Stmt ifStmt,
                    Token elseTk,
                    Stmt elseStmt) {
        this(ifTk, leftParent, cond, rightParent, ifStmt);
        this.elseTk = elseTk;
        this.elseStmt = elseStmt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ifTk.toString());
        sb.append(leftParent.toString());
        sb.append(cond.toString());
        sb.append(rightParent.toString());
        sb.append(ifStmt.toString());
        if (elseTk != null) {
            sb.append(elseTk.toString());
            sb.append(elseStmt.toString());
        }
        return sb.toString();
    }
}
