package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.expression.multiExp.LAndExp;
import frontend.parser.struct.expression.multiExp.LOrExp;

import java.util.ArrayList;

public class LOrExpParser {
    private TokenListIterator iterator;
    /* LOrExp Attributes */
    private LAndExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<LAndExp> operands = new ArrayList<>();

    public LOrExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public LOrExp parseLOrExp() {
        this.operands = new ArrayList<>();
        this.operators = new ArrayList<>();
        LAndExpParser landExpParser = new LAndExpParser(this.iterator);
        this.first = landExpParser.parseLAndExp();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.OR)) { // '||'
            this.operators.add(token);
            this.operands.add(landExpParser.parseLAndExp());
            token = this.iterator.readNextToken();
        }
        this.iterator.unReadToken(1);
        return new LOrExp(this.first, this.operators, this.operands);
    }
}
