package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.CondParser;
import frontend.parser.struct.expression.Cond;
import frontend.parser.struct.statement.ForStmt;
import frontend.parser.struct.statement.stmt.Stmt;
import frontend.parser.struct.statement.stmt.StmtFor;

public class StmtForParser {
    private TokenListIterator iterator;
    private Token forTk = null; // for
    private Token leftParent = null; // '('
    private ForStmt forStmt1 = null; // may exist
    private Token semiTk1 = null; // ';'
    private Cond cond = null; // may exist
    private Token semiTk2 = null; // ';'
    private ForStmt forStmt2 = null; // may exist
    private Token rightParent = null; // ')'
    private Stmt stmt = null;

    public StmtForParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * StmtFor = 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt
     * @return {@link StmtFor}
     */
    public StmtFor parseStmtFor() {
        /* 'for' */
        this.forTk = this.iterator.readNextToken();
        if (!this.forTk.getType().equals(LexType.FORTK)) {
            System.out.println("EXPECT FORTK IN STMTFORPARSER");
        }
        /* '(' */
        this.leftParent = this.iterator.readNextToken();
        if (!this.leftParent.getType().equals(LexType.LPARENT)) {
            System.out.println("EXPECT LEFTPARENT IN STMTFORPARSER");
        }
        this.semiTk1 = this.iterator.readNextToken();
        if (!this.semiTk1.getType().equals(LexType.SEMICN)) {
            /* ForStmt */
            this.iterator.unReadToken(1);
            ForStmtParser forStmtParser = new ForStmtParser(iterator);
            this.forStmt1 = forStmtParser.parseForStmt();
            this.semiTk1 = this.iterator.readNextToken();
        }
        this.semiTk2 = this.iterator.readNextToken();
        if (!this.semiTk2.getType().equals(LexType.SEMICN)) {
            /* Cond */
            this.iterator.unReadToken(1);
            CondParser condParser = new CondParser(iterator);
            this.cond = condParser.parseCond();
            this.semiTk2 = this.iterator.readNextToken();
        }
        this.rightParent = this.iterator.readNextToken();
        if (!this.rightParent.getType().equals(LexType.RPARENT)) {
            /* ForStmt */
            this.iterator.unReadToken(1);
            ForStmtParser forStmtParser = new ForStmtParser(iterator);
            this.forStmt2 = forStmtParser.parseForStmt();
            this.rightParent = this.iterator.readNextToken();
        }

        /* Stmt */
        StmtParser stmtParser = new StmtParser(iterator);
        this.stmt = stmtParser.parseStmt();
        return new StmtFor(forTk, leftParent, forStmt1, semiTk1, cond, semiTk2, forStmt2, rightParent, stmt);
    }
}
