package frontend.parser.parser.expressionParser.primaryExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.expression.primaryExp.PrimaryExp;
import frontend.parser.struct.expression.primaryExp.PrimaryExpEle;

public class PrimaryExpParser {
    private PrimaryExpEle primaryExpEle;

    /**
     * PrimaryExp -> PriMaryExpEle = '(' Exp ')' | LVal | Number
     * @return {@link PrimaryExp}
     */
    public PrimaryExp parsePrimaryExp() {
        Token token = TLIterator.readNextToken();
        TLIterator.unReadToken(1);
        if (token.getType().equals(LexType.LPARENT)) { // '('
            primaryExpEle = new PrimaryExpExpParser().parsePrimaryExpExp();
        } else if (token.getType().equals(LexType.IDENFR)) { // IDENFR
            primaryExpEle = new LValParser().parseLVal();
        } else if (token.getType().equals(LexType.INTCON)) { // INT
            primaryExpEle = new NumberParser().parseNumber();
        }
        return new PrimaryExp(primaryExpEle);
    }
}
