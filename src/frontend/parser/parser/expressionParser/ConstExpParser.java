package frontend.parser.parser.expressionParser;

import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.multiExpParser.AddExpParser;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.expression.multiExp.AddExp;

public class ConstExpParser {
    private TokenListIterator iterator;
    /* ConstExp Attributes */
    private AddExp addExp = null;

    public ConstExpParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * ConstExp → AddExp
     * @return {@link ConstExp}
     */
    public ConstExp parseConstExp() {
        AddExpParser addExpParser = new AddExpParser(this.iterator);
        this.addExp = addExpParser.parseAddExp();
        return new ConstExp(this.addExp);
    }
}
