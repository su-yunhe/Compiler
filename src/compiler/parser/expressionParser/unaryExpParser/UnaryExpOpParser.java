package compiler.parser.expressionParser.unaryExpParser;

import struct.syntaxTree.expression.unaryExp.UnaryExp;
import struct.syntaxTree.expression.unaryExp.UnaryExpOp;
import struct.syntaxTree.expression.unaryExp.UnaryOp;

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
