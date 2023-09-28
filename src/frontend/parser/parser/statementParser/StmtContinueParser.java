package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.statement.stmt.StmtContinue;

public class StmtContinueParser {
    private TokenListIterator iterator;
    /* StmtContinue Attributes */
    private Token continueTk; // 'continue'
    private Token semicn; // ';'

    public StmtContinueParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public StmtContinue parseStmtContinue() {
        this.continueTk = this.iterator.readNextToken();
        if (!this.continueTk.getType().equals(LexType.CONTINUETK)) {
            System.out.println("EXPECT CONTINUETK IN STMTCONTINUEPARSER");
        }
        this.semicn = this.iterator.readNextToken();
        if (!this.semicn.getType().equals(LexType.SEMICN)) {
            System.out.println("EXPECT SEMICN IN STMTCONTINUEPARSER");
        }
        return new StmtContinue(this.continueTk, this.semicn);
    }
}
