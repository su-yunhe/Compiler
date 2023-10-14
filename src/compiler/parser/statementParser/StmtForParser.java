package compiler.parser.statementParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.CondParser;
import struct.syntaxTree.expression.Cond;
import struct.syntaxTree.statement.ForStmt;
import struct.syntaxTree.statement.stmt.Stmt;
import struct.syntaxTree.statement.stmt.StmtFor;
import struct.symbolTable.STStack;

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
        forTk = TLIterator.readNext();
        if (!forTk.getType().equals(LexType.FORTK)) {
            System.out.println("EXPECT FORTK IN STMTFORPARSER");
        }
        /* '(' */
        leftParent = TLIterator.readNext();
        if (!leftParent.getType().equals(LexType.LPARENT)) {
            System.out.println("EXPECT LEFTPARENT IN STMTFORPARSER");
        }
        semiTk1 = TLIterator.readNext();
        if (!semiTk1.getType().equals(LexType.SEMICN)) {
            /* ForStmt */
            TLIterator.unRead(1);
            forStmt1 = new ForStmtParser().parseForStmt();
            semiTk1 = TLIterator.readNext();
        }
        semiTk2 = TLIterator.readNext();
        if (!semiTk2.getType().equals(LexType.SEMICN)) {
            /* Cond */
            TLIterator.unRead(1);
            cond = new CondParser().parseCond();
            semiTk2 = TLIterator.readNext();
        }
        rightParent = TLIterator.readNext();
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            /* ForStmt */
            TLIterator.unRead(1);
            forStmt2 = new ForStmtParser().parseForStmt();
            rightParent = TLIterator.readNext();
        }
        // TODO: 创建循环体的符号表并压栈
        STStack.pushSTFor();
        /* Stmt */
        stmt = new StmtParser().parseStmt();
        // TODO: 弹出循环体的符号表
        STStack.popST();
        return new StmtFor(forTk, leftParent, forStmt1, semiTk1, cond, semiTk2, forStmt2, rightParent, stmt);
    }
}
