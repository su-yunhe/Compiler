package frontend.parser.parser.statementParser;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.parser.expressionParser.primaryExpParser.LValParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.LVal;
import frontend.parser.struct.statement.ForStmt;

public class ForStmtParser {
    private TokenListIterator iterator;
    private LVal lVal = null;
    private Token eq = null; // =
    private Exp exp = null;

    public ForStmtParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * ForStmt → LVal '=' Exp
     * @return {@link ForStmt}
     */
    public ForStmt parseForStmt() {
        LValParser lvalParser = new LValParser(this.iterator);
        this.lVal = lvalParser.parseLVal();
        this.eq = this.iterator.readNextToken();
        ExpParser expParser = new ExpParser(this.iterator);
        this.exp = expParser.parseExp();
        return new ForStmt(lVal, eq, exp);
    }
}
