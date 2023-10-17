package compiler.parser.statementParser;

import enums.LexType;
import compiler.parser.expressionParser.CondParser;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.Cond;
import struct.syntaxTree.statement.stmt.Stmt;
import struct.syntaxTree.statement.stmt.StmtCond;
import utils.ErrorUtils;

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
        ifTK = TLIterator.readNext();
        if (!ifTK.getType().equals(LexType.IFTK)) {
            System.out.println("EXPECT IFTK IN STMTCONDPARSER");
        }
        leftParent = TLIterator.readNext();
        if (!leftParent.getType().equals(LexType.LPARENT)) {
            System.out.println("EXPECT LEFTPARENT IN STMTCONDPARSER");
        }
        cond = new CondParser().parseCond();
        rightParent = TLIterator.readNext();
        // TODO: 处理j类错误：缺失右小括号`)`
        handleJError();
        ifStmt = new StmtParser().parseStmt();
        elseTk = TLIterator.readNext();
        if (elseTk.getType().equals(LexType.ELSETK)) {
            elseStmt = new StmtParser().parseStmt();
            stmtCond = new StmtCond(ifTK, leftParent, cond, rightParent, ifStmt, elseTk, elseStmt);
        } else {
            TLIterator.unRead(1);
            stmtCond = new StmtCond(ifTK, leftParent, cond, rightParent, ifStmt);
        }
        return stmtCond;
    }

    /**
     * 检查并处理 j 类错误：缺失右小括号`)`
     */
    private void handleJError() {
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleJ(prev);
        }
    }
}
