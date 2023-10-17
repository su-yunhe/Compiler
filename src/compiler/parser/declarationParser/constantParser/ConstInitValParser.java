package compiler.parser.declarationParser.constantParser;

import enums.LexType;
import compiler.parser.expressionParser.ConstExpParser;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.declaration.constant.constInitVal.ConstInitVal;
import struct.syntaxTree.declaration.constant.constInitVal.ConstInitValEle;

public class ConstInitValParser {
    /* ConstInitVal Attribute */
    private ConstInitValEle constInitValEle;

    /**
     * ConstInitVal → ConstInitEle = ConstExp | '{' [ ConstInitVal { ',' ConstInitVal } ] '}'
     * @return {@link ConstInitVal}
     */
    public ConstInitVal parseConstInitVal() {
        Token token = TLIterator.readNext();
        TLIterator.unRead(1);
        if (token.getType().equals(LexType.LBRACE)) { // '{'
            constInitValEle = new ConstInitValMultiParser().parseConstInitValMulti();
        } else {
            constInitValEle = new ConstExpParser().parseConstExp();
        }
        return new ConstInitVal(constInitValEle);
    }
}
