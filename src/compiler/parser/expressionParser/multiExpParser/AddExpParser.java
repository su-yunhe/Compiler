package compiler.parser.expressionParser.multiExpParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.multiExp.AddExp;
import struct.syntaxTree.expression.multiExp.MulExp;

import java.util.ArrayList;

public class AddExpParser {
    /* AddExp Attributes */
    private MulExp first = null;
    private ArrayList<Token> operators = new ArrayList<>(); // '+' '-'
    private ArrayList<MulExp> operands = new ArrayList<>();
    /**
     * AddExp -> MulExp { ('+' | '-') MulExp }
     * @return {@link AddExp}
     */
    public AddExp parseAddExp() {
        operands = new ArrayList<>();
        operators = new ArrayList<>();
        /* MulExp */
        first = new MulExpParser().parseMulExp();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.PLUS) ||
                token.getType().equals(LexType.MINU)) { // + -
            operators.add(token);
            MulExp mulExp = new MulExpParser().parseMulExp();
            operands.add(mulExp);
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new AddExp(first, operators, operands);
    }
}