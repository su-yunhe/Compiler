package frontend.parser.parser.expressionParser.unaryExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.expression.unaryExp.UnaryOp;

public class UnaryOpParser {
    private TokenListIterator iterator;

    public UnaryOpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public UnaryOp parseUnaryOp() {
        Token token = this.iterator.readNextToken();
        if (!(token.getType().equals(LexType.PLUS) ||
                token.getType().equals(LexType.MINU) ||
                token.getType().equals(LexType.NOT))) {
            System.out.println("EXPECT UNARYOP HERE");
        }
        return new UnaryOp(token);
    }
}
