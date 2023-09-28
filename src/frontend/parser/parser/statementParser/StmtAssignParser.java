package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.parser.expressionParser.primaryExpParser.LValParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.LVal;
import frontend.parser.struct.statement.stmt.StmtAssign;

public class StmtAssignParser {
    private TokenListIterator iterator;
    /* StmtAssign Attributes */
    private LVal lval = null;
    private Token eq; // '='
    private Exp exp;
    private Token semicn; // ';'

    public StmtAssignParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * StmtAssign = LVal '=' Exp ';'
     * @return {@link StmtAssign}
     */
    public StmtAssign parseStmtAssign() {
        LValParser lvalParser = new LValParser(this.iterator);
        this.lval = lvalParser.parseLVal();
        this.eq = this.iterator.readNextToken();
        if (!this.eq.getType().equals(LexType.ASSIGN)) {
            System.out.println("EXPECT = HERE");
        }
        ExpParser expParser = new ExpParser(this.iterator);
        this.exp = expParser.parseExp();
        this.semicn = this.iterator.readNextToken();
        return new StmtAssign(this.lval, this.eq, this.exp, this.semicn);
    }
}
