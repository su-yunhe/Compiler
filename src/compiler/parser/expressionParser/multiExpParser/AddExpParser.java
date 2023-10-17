package compiler.parser.expressionParser.multiExpParser;

import enums.LexType;
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
        operators = new ArrayList<>();
        operands = new ArrayList<>();

        /* MulExp */
        first = new MulExpParser().parseMulExp();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.PLUS) ||
                token.getType().equals(LexType.MINU)) { // + -
            operators.add(token);
            operands.add(new MulExpParser().parseMulExp());
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new AddExp(first, operators, operands);
    }
}
