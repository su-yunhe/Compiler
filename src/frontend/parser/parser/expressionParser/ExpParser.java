package frontend.parser.parser.expressionParser;

import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.multiExpParser.AddExpParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.multiExp.AddExp;

public class ExpParser {
    private TokenListIterator iterator;

    public ExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * Exp → AddExp
     * @return {@link Exp}
     */
    public Exp parseExp() {
        AddExpParser addExpParser = new AddExpParser(this.iterator);
        AddExp addExp = addExpParser.parseAddExp();
        return new Exp(addExp);
    }
}
