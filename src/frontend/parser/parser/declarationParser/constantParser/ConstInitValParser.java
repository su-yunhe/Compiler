package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ConstExpParser;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitVal;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitValEle;

public class ConstInitValParser {
    /* ConstInitVal Attribute */
    private ConstInitValEle constInitValEle;

    /**
     * ConstInitVal → ConstInitEle = ConstExp | '{' [ ConstInitVal { ',' ConstInitVal } ] '}'
     * @return {@link ConstInitVal}
     */
    public ConstInitVal parseConstInitVal() {
        Token token = TLIterator.readNextToken();
        TLIterator.unReadToken(1);
        if (token.getType().equals(LexType.LBRACE)) { // '{'
            constInitValEle = new ConstInitValMultiParser().parseConstInitValMulti();
        } else {
            constInitValEle = new ConstExpParser().parseConstExp();
        }
        return new ConstInitVal(constInitValEle);
    }
}
