package compiler.parser.statementParser;

import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.ExpParser;
import compiler.parser.expressionParser.primaryExpParser.LValParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.primaryExp.LVal;
import struct.syntaxTree.statement.ForStmt;

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
        eq = TLIterator.readNext();
        exp = new ExpParser().parseExp();
        return new ForStmt(lVal, eq, exp);
    }
}
