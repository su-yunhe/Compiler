package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.statement.stmt.StmtContinue;

public class StmtContinueParser {
    /* StmtContinue Attributes */
    private Token continueTk; // 'continue'
    private Token semicn; // ';'
    public StmtContinue parseStmtContinue() {
        continueTk = TLIterator.readNextToken();
        if (!continueTk.getType().equals(LexType.CONTINUETK)) {
            System.out.println("EXPECT CONTINUETK IN STMTCONTINUEPARSER");
        }
        semicn = TLIterator.readNextToken();
        if (!semicn.getType().equals(LexType.SEMICN)) {
            System.out.println("EXPECT SEMICN IN STMTCONTINUEPARSER");
        }
        return new StmtContinue(continueTk, semicn);
    }
}
