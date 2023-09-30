package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.unaryExpParser.UnaryExpParser;
import frontend.parser.struct.expression.multiExp.MulExp;
import frontend.parser.struct.expression.unaryExp.UnaryExp;

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
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.MULT) ||
                token.getType().equals(LexType.DIV) ||
                token.getType().equals(LexType.MOD)) {
            operators.add(token);
            UnaryExp unaryExp = new UnaryExpParser().parseUnaryExp();
            operands.add(unaryExp);
            token = TLIterator.readNextToken();
        }
        TLIterator.unReadToken(1);
        return new MulExp(first, operators, operands);
    }
}
