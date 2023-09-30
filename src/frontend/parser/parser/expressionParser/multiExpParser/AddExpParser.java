package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.expression.multiExp.AddExp;
import frontend.parser.struct.expression.multiExp.MulExp;

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
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.PLUS) ||
                token.getType().equals(LexType.MINU)) { // + -
            operators.add(token);
            MulExp mulExp = new MulExpParser().parseMulExp();
            operands.add(mulExp);
            token = TLIterator.readNextToken();
        }
        TLIterator.unReadToken(1);
        return new AddExp(first, operators, operands);
    }
}
