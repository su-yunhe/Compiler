package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.statement.stmt.StmtBreak;

public class StmtBreakParser {
    /* StmtBreak Attributes */
    private Token breakTk; // 'break'
    private Token semicn; // ';'
    public StmtBreak parseStmtBreak() {
        breakTk = TLIterator.readNextToken();
        if (!this.breakTk.getType().equals(LexType.BREAKTK)) {
            System.out.println("EXPECT BREAKTK IN STMTBREAKPARSER");
        }
        semicn = TLIterator.readNextToken();
        if (!this.semicn.getType().equals(LexType.SEMICN)) {
            System.out.println("EXPECT SEMICN IN STMTBREAKPARSER");
        }
        return new StmtBreak(breakTk, semicn);
    }
}
