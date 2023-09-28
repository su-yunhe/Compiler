package frontend.parser.parser.expressionParser.primaryExpParser;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.PrimaryExpExp;

public class PrimaryExpExpParser {
    private TokenListIterator iterator;
    /* PrimaryExpExp Attribute */
    private Token leftParent = null; // '('
    private Exp exp = null;
    private Token rightParent = null; // ')'

    public PrimaryExpExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public PrimaryExpExp parsePrimaryExpExp() {
        this.leftParent = this.iterator.readNextToken();
        ExpParser expParser = new ExpParser(this.iterator);
        this.exp = expParser.parseExp();
        this.rightParent = this.iterator.readNextToken();
        return new PrimaryExpExp(this.leftParent, this.exp, this.rightParent);
    }
}
