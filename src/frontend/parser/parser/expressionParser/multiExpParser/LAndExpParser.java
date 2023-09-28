package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.expression.multiExp.EqExp;
import frontend.parser.struct.expression.multiExp.LAndExp;

import java.util.ArrayList;

public class LAndExpParser {
    private TokenListIterator iterator;
    /* LAndExp Attributes */
    private EqExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<EqExp> operands = new ArrayList<>();

    public LAndExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public LAndExp parseLAndExp() {
        this.operands = new ArrayList<>();
        this.operators = new ArrayList<>();
        EqExpParser eqExpParser = new EqExpParser(this.iterator);
        this.first = eqExpParser.parseEqExp();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.AND)) { // '&&'
            this.operators.add(token);
            this.operands.add(eqExpParser.parseEqExp());
            token = this.iterator.readNextToken();
        }
        this.iterator.unReadToken(1);
        return new LAndExp(this.first, this.operators, this.operands);
    }
}
