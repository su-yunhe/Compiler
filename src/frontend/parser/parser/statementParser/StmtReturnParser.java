package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.statement.stmt.StmtReturn;

public class StmtReturnParser {
    /* StmtPrint Attributes */
    private Token returnTk; // 'return'
    private Exp exp;
    private Token semicn; // ';'
    private StmtReturn stmtReturn = null;
    public StmtReturn parseStmtReturn() {
        this.returnTk = TLIterator.readNextToken();
        if (!this.returnTk.getType().equals(LexType.RETURNTK)) {
            System.out.println("EXPECT RETURNTK IN STMTRETURNPARSER");
        }
        ExpParser expParser = new ExpParser();
        this.semicn = TLIterator.readNextToken();
        if (!this.semicn.getType().equals(LexType.SEMICN)) {
            TLIterator.unReadToken(1);
            this.exp = expParser.parseExp();
            this.semicn = TLIterator.readNextToken();
            stmtReturn = new StmtReturn(this.returnTk, this.exp, this.semicn);
        } else {
            stmtReturn = new StmtReturn(this.returnTk, this.semicn);
        }
        return stmtReturn;
    }
}
