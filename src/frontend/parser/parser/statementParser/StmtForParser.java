package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.CondParser;
import frontend.parser.struct.expression.Cond;
import frontend.parser.struct.statement.ForStmt;
import frontend.parser.struct.statement.stmt.Stmt;
import frontend.parser.struct.statement.stmt.StmtFor;

public class StmtForParser {
    private Token forTk = null; // for
    private Token leftParent = null; // '('
    private ForStmt forStmt1 = null; // may exist
    private Token semiTk1 = null; // ';'
    private Cond cond = null; // may exist
    private Token semiTk2 = null; // ';'
    private ForStmt forStmt2 = null; // may exist
    private Token rightParent = null; // ')'
    private Stmt stmt = null;

    /**
     * StmtFor = 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
     * @return {@link StmtFor}
     */
    public StmtFor parseStmtFor() {
        /* 'for' */
        forTk = TLIterator.readNextToken();
        if (!forTk.getType().equals(LexType.FORTK)) {
            System.out.println("EXPECT FORTK IN STMTFORPARSER");
        }
        /* '(' */
        leftParent = TLIterator.readNextToken();
        if (!leftParent.getType().equals(LexType.LPARENT)) {
            System.out.println("EXPECT LEFTPARENT IN STMTFORPARSER");
        }
        semiTk1 = TLIterator.readNextToken();
        if (!semiTk1.getType().equals(LexType.SEMICN)) {
            /* ForStmt */
            TLIterator.unReadToken(1);
            forStmt1 = new ForStmtParser().parseForStmt();
            semiTk1 = TLIterator.readNextToken();
        }
        semiTk2 = TLIterator.readNextToken();
        if (!semiTk2.getType().equals(LexType.SEMICN)) {
            /* Cond */
            TLIterator.unReadToken(1);
            cond = new CondParser().parseCond();
            semiTk2 = TLIterator.readNextToken();
        }
        rightParent = TLIterator.readNextToken();
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            /* ForStmt */
            TLIterator.unReadToken(1);
            forStmt2 = new ForStmtParser().parseForStmt();
            rightParent = TLIterator.readNextToken();
        }

        /* Stmt */
        stmt = new StmtParser().parseStmt();
        return new StmtFor(forTk, leftParent, forStmt1, semiTk1, cond, semiTk2, forStmt2, rightParent, stmt);
    }
}
