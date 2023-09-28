package frontend.parser.parser.expressionParser.unaryExpParser;

import frontend.lexer.TokenListIterator;
import frontend.parser.struct.expression.unaryExp.UnaryExp;
import frontend.parser.struct.expression.unaryExp.UnaryExpOp;
import frontend.parser.struct.expression.unaryExp.UnaryOp;

public class UnaryExpOpParser {
    private TokenListIterator iterator;
    /* UnaryExpOp Attributes */
    private UnaryOp unaryOp;
    private UnaryExp unaryExp;

    public UnaryExpOpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public UnaryExpOp parseUnaryExpOp() {
        UnaryOpParser unaryOpParser = new UnaryOpParser(this.iterator);
        this.unaryOp = unaryOpParser.parseUnaryOp();
        UnaryExpParser unaryExpParser = new UnaryExpParser(this.iterator);
        this.unaryExp = unaryExpParser.parseUnaryExp();
        return new UnaryExpOp(this.unaryOp, this.unaryExp);
    }
}
