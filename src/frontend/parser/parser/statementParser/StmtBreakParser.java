package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.statement.stmt.StmtBreak;

public class StmtBreakParser {
    private TokenListIterator iterator;
    /* StmtBreak Attributes */
    private Token breakTk; // 'break'
    private Token semicn; // ';'

    public StmtBreakParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public StmtBreak parseStmtBreak() {
        this.breakTk = this.iterator.readNextToken();
        if (!this.breakTk.getType().equals(LexType.BREAKTK)) {
            System.out.println("EXPECT BREAKTK IN STMTBREAKPARSER");
        }
        this.semicn = this.iterator.readNextToken();
        if (!this.semicn.getType().equals(LexType.SEMICN)) {
            System.out.println("EXPECT SEMICN IN STMTBREAKPARSER");
        }
        return new StmtBreak(this.breakTk, this.semicn);
    }
}
