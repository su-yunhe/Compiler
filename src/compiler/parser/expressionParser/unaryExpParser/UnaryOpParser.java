package compiler.parser.expressionParser.unaryExpParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.unaryExp.UnaryOp;

public class UnaryOpParser {
    public UnaryOp parseUnaryOp() {
        Token token = TLIterator.readNext();
        if (!(token.getType().equals(LexType.PLUS) ||
                token.getType().equals(LexType.MINU) ||
                token.getType().equals(LexType.NOT))) {
            System.out.println("EXPECT UNARYOP HERE");
        }
        return new UnaryOp(token);
    }
}
