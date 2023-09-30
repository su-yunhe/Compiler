package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.expression.multiExp.EqExp;
import frontend.parser.struct.expression.multiExp.RelExp;

import java.util.ArrayList;

public class EqExpParser {
    /* EqExp Attributes */
    private RelExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<RelExp> operands = new ArrayList<>();
    public EqExp parseEqExp() {
        operands = new ArrayList<>();
        operators = new ArrayList<>();
        first = new RelExpParser().parseRelExp();
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.EQL) || // '=='
                token.getType().equals(LexType.NEQ)) { // '!='
            operators.add(token);
            operands.add(new RelExpParser().parseRelExp());
            token = TLIterator.readNextToken();
        }
        TLIterator.unReadToken(1);
        return new EqExp(first, operators, operands);
    }
}
