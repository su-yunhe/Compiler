package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.statement.stmt.StmtReturn;

public class StmtReturnParser {
    private TokenListIterator iterator;
    /* StmtPrint Attributes */
    private Token returnTk; // 'return'
    private Exp exp;
    private Token semicn; // ';'
    private StmtReturn stmtReturn = null;

    public StmtReturnParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public StmtReturn parseStmtReturn() {
        this.returnTk = this.iterator.readNextToken();
        if (!this.returnTk.getType().equals(LexType.RETURNTK)) {
            System.out.println("EXPECT RETURNTK IN STMTRETURNPARSER");
        }
        ExpParser expParser = new ExpParser(this.iterator);
        this.semicn = this.iterator.readNextToken();
        if (!this.semicn.getType().equals(LexType.SEMICN)) {
            this.iterator.unReadToken(1);
            this.exp = expParser.parseExp();
            this.semicn = this.iterator.readNextToken();
            stmtReturn = new StmtReturn(this.returnTk, this.exp, this.semicn);
        } else {
            stmtReturn = new StmtReturn(this.returnTk, this.semicn);
        }
        return stmtReturn;
    }
}
