package frontend.parser.parser.statementParser;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.statement.stmt.StmtExp;

public class StmtExpParser {
    private TokenListIterator iterator;
    /* StmtExp Attributes */
    private Exp exp = null;
    private Token semicn = null; // ';'

    public StmtExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public StmtExp parseStmtExp() {
        ExpParser expParser = new ExpParser(this.iterator);
        this.exp = expParser.parseExp();
        this.semicn = this.iterator.readNextToken();
        return new StmtExp(this.exp, this.semicn);
    }
}
