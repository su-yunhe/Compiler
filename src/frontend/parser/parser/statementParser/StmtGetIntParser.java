package frontend.parser.parser.statementParser;

import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.primaryExpParser.LValParser;
import frontend.parser.struct.expression.primaryExp.LVal;
import frontend.parser.struct.statement.stmt.StmtGetInt;

public class StmtGetIntParser {
    /* StmtGetint Attributes */
    private LVal lval = null;
    private Token eq = null; // '='
    private Token getint = null; // 'getint'
    private Token leftParent = null; // '('
    private Token rightParent = null; // ')'
    private Token semicn = null; // ';'
    /**
     * LVal '=' 'getint''('')'';'
     * @return {@link StmtGetInt}
     */
    public StmtGetInt parseStmtGetInt() {
        lval = new LValParser().parseLVal();
        eq = TLIterator.readNextToken();
        getint = TLIterator.readNextToken();
        leftParent = TLIterator.readNextToken();
        rightParent = TLIterator.readNextToken();
        semicn = TLIterator.readNextToken();
        return new StmtGetInt(lval, eq, getint, leftParent, rightParent, semicn);
    }
}
