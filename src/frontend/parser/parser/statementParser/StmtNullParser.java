package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.statement.stmt.StmtNull;

public class StmtNullParser {
    /* StmtNull Attributes */
    private Token semicn = null; // ';'
    public StmtNull pasreStmtNull() {
        semicn = TLIterator.readNextToken();
        StmtNull stmtNull = new StmtNull(this.semicn);
        if (!semicn.getType().equals(LexType.SEMICN)) {
            System.out.println("EXPECT SEMICN IN STMTNULLPARSER");
        }
        return stmtNull;
    }
}
