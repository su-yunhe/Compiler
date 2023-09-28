package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.unaryExpParser.UnaryExpParser;
import frontend.parser.struct.expression.multiExp.MulExp;
import frontend.parser.struct.expression.unaryExp.UnaryExp;

import java.util.ArrayList;

public class MulExpParser {
    private TokenListIterator iterator;
    /* MulExp Attributes */
    private UnaryExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<UnaryExp> operands = new ArrayList<>();

    public MulExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * MulExp -> UnaryExp { ('*' | '/' | '%') UnaryExp }
     * @return {@link MulExp}
     */
    public MulExp parseMulExp() {
        this.operators = new ArrayList<>();
        this.operands = new ArrayList<>();
        /* UnaryExp */
        UnaryExpParser unaryExpParser = new UnaryExpParser(this.iterator);
        this.first = unaryExpParser.parseUnaryExp();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.MULT) ||
                token.getType().equals(LexType.DIV) ||
                token.getType().equals(LexType.MOD)) {
            this.operators.add(token);
            this.operands.add(unaryExpParser.parseUnaryExp());
            token = this.iterator.readNextToken();
        }
        this.iterator.unReadToken(1);
        return new MulExp(this.first, this.operators, this.operands);
    }
}
