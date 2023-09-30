package frontend.parser.parser.expressionParser.unaryExpParser;

import frontend.parser.TLIterator;
import frontend.parser.struct.expression.unaryExp.UnaryExp;
import frontend.parser.struct.expression.unaryExp.UnaryExpOp;
import frontend.parser.struct.expression.unaryExp.UnaryOp;

public class UnaryExpOpParser {
    /* UnaryExpOp Attributes */
    private UnaryOp unaryOp;
    private UnaryExp unaryExp;

    /**
     * UnaryExpOp = UnaryOp UnaryExp
     * @return {@link UnaryExpOp}
     */
    public UnaryExpOp parseUnaryExpOp() {
        unaryOp = new UnaryOpParser().parseUnaryOp();
        unaryExp = new UnaryExpParser().parseUnaryExp();
        return new UnaryExpOp(unaryOp, unaryExp);
    }
}
