package compiler.parser.expressionParser.primaryExpParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.primaryExp.PrimaryExp;
import struct.syntaxTree.expression.primaryExp.PrimaryExpEle;

public class PrimaryExpParser {
    private PrimaryExpEle primaryExpEle = null;

    /**
     * PrimaryExp -> PriMaryExpEle = '(' Exp ')' | LVal | Number
     * @return {@link PrimaryExp}
     */
    public PrimaryExp parsePrimaryExp() {
        Token token = TLIterator.readNext();
        TLIterator.unRead(1);
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
