package frontend.parser.parser.expressionParser;

import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.multiExpParser.AddExpParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.multiExp.AddExp;

public class ExpParser {
    /**
     * Exp → AddExp
     * @return {@link Exp}
     */
    public Exp parseExp() {
        AddExp addExp = new AddExpParser().parseAddExp();
        return new Exp(addExp);
    }
}
