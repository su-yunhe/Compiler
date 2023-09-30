package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.parser.expressionParser.primaryExpParser.LValParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.LVal;
import frontend.parser.struct.statement.stmt.StmtAssign;

public class StmtAssignParser {
    /* StmtAssign Attributes */
    private LVal lval = null;
    private Token eq; // '='
    private Exp exp;
    private Token semicn; // ';'
    
    /**
     * StmtAssign = LVal '=' Exp ';'
     * @return {@link StmtAssign}
     */
    public StmtAssign parseStmtAssign() {
        lval = new LValParser().parseLVal();
        eq = TLIterator.readNextToken();
        if (!eq.getType().equals(LexType.ASSIGN)) {
            System.out.println("EXPECT = HERE");
        }
        exp = new ExpParser().parseExp();
        semicn = TLIterator.readNextToken();
        return new StmtAssign(lval, eq, exp, semicn);
    }
}
