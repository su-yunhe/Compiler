package frontend.parser.parser.expressionParser.primaryExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.expression.primaryExp.PrimaryExp;
import frontend.parser.struct.expression.primaryExp.PrimaryExpEle;

public class PrimaryExpParser {
    private TokenListIterator iterator;
    /* PrimaryExp Attributes */
    private PrimaryExpEle primaryExpEle;

    public PrimaryExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public PrimaryExp parsePrimaryExp() {
        Token token = this.iterator.readNextToken();
        if (token.getType().equals(LexType.LPARENT)) { // '('
            this.iterator.unReadToken(1);
            PrimaryExpExpParser primaryExpExpParser = new PrimaryExpExpParser(this.iterator);
            this.primaryExpEle = primaryExpExpParser.parsePrimaryExpExp();
        } else if (token.getType().equals(LexType.IDENFR)) { // IDENFR
            this.iterator.unReadToken(1);
            LValParser lvalParser = new LValParser(this.iterator);
            this.primaryExpEle = lvalParser.parseLVal();
        } else if (token.getType().equals(LexType.INTCON)) { // INT
            this.iterator.unReadToken(1);
            NumberParser numberParser = new NumberParser(this.iterator);
            this.primaryExpEle = numberParser.parseNumber();
        }
        return new PrimaryExp(this.primaryExpEle);
    }
}
