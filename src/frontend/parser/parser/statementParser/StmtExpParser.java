package frontend.parser.parser.statementParser;

import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.statement.stmt.StmtExp;

public class StmtExpParser {
    /* StmtExp Attributes */
    private Exp exp = null;
    private Token semicn = null; // ';'
    public StmtExp parseStmtExp() {
        ExpParser expParser = new ExpParser();
        exp = expParser.parseExp();
        semicn = TLIterator.readNextToken();
        return new StmtExp(exp, semicn);
    }
}
