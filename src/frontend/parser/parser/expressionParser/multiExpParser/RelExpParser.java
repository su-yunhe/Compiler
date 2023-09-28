package frontend.parser.parser.expressionParser.multiExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.expression.multiExp.AddExp;
import frontend.parser.struct.expression.multiExp.RelExp;

import java.util.ArrayList;

public class RelExpParser {
    private TokenListIterator iterator;
    /* RelExp Attributes */
    private AddExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<AddExp> operands = new ArrayList<>();

    public RelExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public RelExp parseRelExp() {
        this.operands = new ArrayList<>();
        this.operators = new ArrayList<>();
        AddExpParser addExpParser = new AddExpParser(this.iterator);
        first = addExpParser.parseAddExp();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.LSS) || // <
                token.getType().equals(LexType.GRE) || // >
                token.getType().equals(LexType.LEQ) || // <=
                token.getType().equals(LexType.GEQ)) { // >=
            this.operators.add(token);
            this.operands.add(addExpParser.parseAddExp());
            token = this.iterator.readNextToken();
        }
        this.iterator.unReadToken(1);
        return new RelExp(this.first, this.operators, this.operands);
    }
}
