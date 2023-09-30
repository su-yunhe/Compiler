package frontend.parser.parser.expressionParser;

import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.multiExpParser.AddExpParser;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.expression.multiExp.AddExp;

public class ConstExpParser {
    /* ConstExp Attributes */
    private AddExp addExp = null;
    /**
     * ConstExp → AddExp
     * @return {@link ConstExp}
     */
    public ConstExp parseConstExp() {
        addExp = new AddExpParser().parseAddExp();
        return new ConstExp(this.addExp);
    }
}
