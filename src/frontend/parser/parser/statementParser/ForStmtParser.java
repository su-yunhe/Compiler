package frontend.parser.parser.statementParser;

import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.parser.expressionParser.primaryExpParser.LValParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.LVal;
import frontend.parser.struct.statement.ForStmt;

public class ForStmtParser {
    private LVal lVal = null;
    private Token eq = null; // =
    private Exp exp = null;
    /**
     * ForStmt → LVal '=' Exp
     * @return {@link ForStmt}
     */
    public ForStmt parseForStmt() {
        lVal = new LValParser().parseLVal();
        eq = TLIterator.readNextToken();
        exp = new ExpParser().parseExp();
        return new ForStmt(lVal, eq, exp);
    }
}
