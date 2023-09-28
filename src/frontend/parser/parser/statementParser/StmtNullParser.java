package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.statement.stmt.StmtNull;

public class StmtNullParser {
    private TokenListIterator iterator;
    /* StmtNull Attributes */
    private Token semicn = null; // ';'

    public StmtNullParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public StmtNull pasreStmtNull() {
        this.semicn = this.iterator.readNextToken();
        StmtNull stmtNull = new StmtNull(this.semicn);
        if (!this.semicn.getType().equals(LexType.SEMICN)) {
            System.out.println("EXPECT SEMICN IN STMTNULLPARSER");
        }
        return stmtNull;
    }
}
