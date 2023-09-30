package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.expression.multiExp.AddExp;
import frontend.parser.struct.expression.multiExp.RelExp;

import java.util.ArrayList;

public class RelExpParser {
    /* RelExp Attributes */
    private AddExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<AddExp> operands = new ArrayList<>();
    public RelExp parseRelExp() {
        operands = new ArrayList<>();
        operators = new ArrayList<>();
        AddExpParser addExpParser = new AddExpParser();
        first = addExpParser.parseAddExp();
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.LSS) || // <
                token.getType().equals(LexType.GRE) || // >
                token.getType().equals(LexType.LEQ) || // <=
                token.getType().equals(LexType.GEQ)) { // >=
            operators.add(token);
            operands.add(addExpParser.parseAddExp());
            token = TLIterator.readNextToken();
        }
        TLIterator.unReadToken(1);
        return new RelExp(first, operators, operands);
    }
}
