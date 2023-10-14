package compiler.parser.statementParser;

import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.statement.stmt.StmtNull;

public class StmtNullParser {
    /* StmtNull Attributes */
    private Token semicn = null; // ';'
    public StmtNull pasreStmtNull() {
        semicn = TLIterator.readNext();
        StmtNull stmtNull = new StmtNull(this.semicn);
        return stmtNull;
    }
}
