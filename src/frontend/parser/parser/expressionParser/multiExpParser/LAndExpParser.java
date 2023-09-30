package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.expression.multiExp.EqExp;
import frontend.parser.struct.expression.multiExp.LAndExp;

import java.util.ArrayList;

public class LAndExpParser {
    /* LAndExp Attributes */
    private EqExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<EqExp> operands = new ArrayList<>();
    public LAndExp parseLAndExp() {
        operands = new ArrayList<>();
        operators = new ArrayList<>();
        EqExpParser eqExpParser = new EqExpParser();
        first = eqExpParser.parseEqExp();
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.AND)) { // '&&'
            operators.add(token);
            operands.add(eqExpParser.parseEqExp());
            token = TLIterator.readNextToken();
        }
        TLIterator.unReadToken(1);
        return new LAndExp(first, operators, operands);
    }
}
