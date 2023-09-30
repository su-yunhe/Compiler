package frontend.parser.parser.expressionParser.primaryExpParser;

import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.PrimaryExpExp;

public class PrimaryExpExpParser {
    /* PrimaryExpExp Attribute */
    private Token leftParent = null; // '('
    private Exp exp = null;
    private Token rightParent = null; // ')'

    /**
     * PrimaryExpExp = '(' Exp ')'
     * @return {@link PrimaryExpExp}
     */
    public PrimaryExpExp parsePrimaryExpExp() {
        leftParent = TLIterator.readNextToken();
        exp = new ExpParser().parseExp();
        rightParent = TLIterator.readNextToken();
        return new PrimaryExpExp(leftParent, exp, rightParent);
    }
}
