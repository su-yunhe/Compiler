package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.ConstExpParser;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitVal;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitValEle;

public class ConstInitValParser {
    private TokenListIterator iterator;
    /* ConstInitVal Attribute */
    private ConstInitValEle constInitValEle;

    public ConstInitValParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public ConstInitVal parseConstInitVal() {
        Token token = this.iterator.readNextToken();
        if (token.getType().equals(LexType.LBRACE)) { // '{'
            this.iterator.unReadToken(1);
            ConstInitValMultiParser constInitValMultiParser =
                    new ConstInitValMultiParser(this.iterator);
            this.constInitValEle = constInitValMultiParser.parseConstInitValMulti();
        } else {
            this.iterator.unReadToken(1);
            ConstExpParser constExpParser = new ConstExpParser(this.iterator);
            this.constInitValEle = constExpParser.parseConstExp();
        }
        return new ConstInitVal(this.constInitValEle);
    }
}
