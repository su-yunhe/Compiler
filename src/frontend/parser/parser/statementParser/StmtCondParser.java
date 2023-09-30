package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.CondParser;
import frontend.parser.struct.expression.Cond;
import frontend.parser.struct.statement.stmt.Stmt;
import frontend.parser.struct.statement.stmt.StmtCond;

public class StmtCondParser {
    /* StmtCond Attributes */
    private Token ifTK = null; // 'if'
    private Token leftParent = null; // '('
    private Cond cond = null;
    private Token rightParent = null; // ')'
    private Stmt ifStmt = null;
    private Token elseTk = null; // MAY exist 'else'
    private Stmt elseStmt = null; // MAY exist else statement
    private StmtCond stmtCond = null;

    public StmtCond parseStmtCond() {
        ifTK = TLIterator.readNextToken();
        if (!ifTK.getType().equals(LexType.IFTK)) {
            System.out.println("EXPECT IFTK IN STMTCONDPARSER");
        }
        leftParent = TLIterator.readNextToken();
        if (!leftParent.getType().equals(LexType.LPARENT)) {
            System.out.println("EXPECT LEFTPARENT IN STMTCONDPARSER");
        }
        cond = new CondParser().parseCond();
        rightParent = TLIterator.readNextToken();
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            System.out.println("EXPECT RPARENT IN STMTCONDPARSER");
        }
        ifStmt = new StmtParser().parseStmt();
        elseTk = TLIterator.readNextToken();
        if (elseTk.getType().equals(LexType.ELSETK)) {
            elseStmt = new StmtParser().parseStmt();
            stmtCond = new StmtCond(ifTK, leftParent, cond, rightParent, ifStmt, elseTk, elseStmt);
        } else {
            TLIterator.unReadToken(1);
            stmtCond = new StmtCond(ifTK, leftParent, cond, rightParent, ifStmt);
        }
        return stmtCond;
    }
}
