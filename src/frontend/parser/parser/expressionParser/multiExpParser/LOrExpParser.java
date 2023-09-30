package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.expression.multiExp.LAndExp;
import frontend.parser.struct.expression.multiExp.LOrExp;

import java.util.ArrayList;

public class LOrExpParser {
    /* LOrExp Attributes */
    private LAndExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<LAndExp> operands = new ArrayList<>();
    public LOrExp parseLOrExp() {
        operands = new ArrayList<>();
        operators = new ArrayList<>();
        LAndExpParser landExpParser = new LAndExpParser();
        first = landExpParser.parseLAndExp();
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.OR)) { // '||'
            operators.add(token);
            operands.add(landExpParser.parseLAndExp());
            token = TLIterator.readNextToken();
        }
        TLIterator.unReadToken(1);
        return new LOrExp(first, operators, operands);
    }
}
