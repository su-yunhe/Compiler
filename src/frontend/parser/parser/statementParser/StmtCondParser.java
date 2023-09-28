package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.CondParser;
import frontend.parser.struct.expression.Cond;
import frontend.parser.struct.statement.stmt.Stmt;
import frontend.parser.struct.statement.stmt.StmtCond;

public class StmtCondParser {
    private TokenListIterator iterator;
    /* StmtCond Attributes */
    private Token ifTK = null; // 'if'
    private Token leftParent = null; // '('
    private Cond cond = null;
    private Token rightParent = null; // ')'
    private Stmt ifStmt = null;
    private Token elseTk = null; // MAY exist 'else'
    private Stmt elseStmt = null; // MAY exist else statement
    private StmtCond stmtCond = null;

    public StmtCondParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public StmtCond parseStmtCond() {
        this.ifTK = this.iterator.readNextToken();
        if (!this.ifTK.getType().equals(LexType.IFTK)) {
            System.out.println("EXPECT IFTK IN STMTCONDPARSER");
        }
        this.leftParent = this.iterator.readNextToken();
        if (!this.leftParent.getType().equals(LexType.LPARENT)) {
            System.out.println("EXPECT LEFTPARENT IN STMTCONDPARSER");
        }
        CondParser condParser = new CondParser(this.iterator);
        this.cond = condParser.parseCond();
        this.rightParent = this.iterator.readNextToken();
        if (!this.rightParent.getType().equals(LexType.RPARENT)) {
            System.out.println("EXPECT RPARENT IN STMTCONDPARSER");
        }
        StmtParser stmtParser = new StmtParser(this.iterator);
        this.ifStmt = stmtParser.parseStmt();
        this.elseTk = this.iterator.readNextToken();
        if (this.elseTk.getType().equals(LexType.ELSETK)) {
            this.elseStmt = stmtParser.parseStmt();
            this.stmtCond = new StmtCond(this.ifTK, this.leftParent,
                    this.cond, this.rightParent, this.ifStmt, this.elseTk, this.elseStmt);
        } else {
            this.iterator.unReadToken(1);
            this.stmtCond = new StmtCond(this.ifTK, this.leftParent,
                    this.cond, this.rightParent, this.ifStmt);
        }
        return this.stmtCond;
    }
}
