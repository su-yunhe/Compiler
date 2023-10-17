package compiler.parser.expressionParser.multiExpParser;

import enums.LexType;
import compiler.parser.expressionParser.unaryExpParser.UnaryExpParser;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.multiExp.MulExp;
import struct.syntaxTree.expression.unaryExp.UnaryExp;

import java.util.ArrayList;

public class MulExpParser {
    /* MulExp Attributes */
    private UnaryExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<UnaryExp> operands = new ArrayList<>();
    /**
     * MulExp -> UnaryExp { ('*' | '/' | '%') UnaryExp }
     * @return {@link MulExp}
     */
    public MulExp parseMulExp() {
        operators = new ArrayList<>();
        operands = new ArrayList<>();
        /* UnaryExp */
        first = new UnaryExpParser().parseUnaryExp();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.MULT) ||
                token.getType().equals(LexType.DIV) ||
                token.getType().equals(LexType.MOD)) {
            operators.add(token);
            UnaryExp unaryExp = new UnaryExpParser().parseUnaryExp();
            operands.add(unaryExp);
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new MulExp(first, operators, operands);
    }
}
