package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.expression.multiExp.AddExp;
import frontend.parser.struct.expression.multiExp.MulExp;

import java.util.ArrayList;

public class AddExpParser {
    private TokenListIterator iterator;
    /* AddExp Attributes */
    private MulExp first = null;
    private ArrayList<Token> operators = new ArrayList<>(); // '+' '-'
    private ArrayList<MulExp> operands = new ArrayList<>();

    public AddExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * AddExp -> MulExp { ('+' | '-') MulExp }
     * @return {@link AddExp}
     */
    public AddExp parseAddExp() {
        this.operands = new ArrayList<>();
        this.operators = new ArrayList<>();
        /* MulExp */
        MulExpParser mulExpParser = new MulExpParser(this.iterator);
        this.first = mulExpParser.parseMulExp();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.PLUS) ||
                token.getType().equals(LexType.MINU)) { // + -
            this.operators.add(token);
            this.operands.add(mulExpParser.parseMulExp());
            token = this.iterator.readNextToken();
        }
        this.iterator.unReadToken(1);
        return new AddExp(this.first, this.operators, this.operands);
    }
}
