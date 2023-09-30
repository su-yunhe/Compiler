package frontend.parser.parser.expressionParser;

import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.multiExpParser.LOrExpParser;
import frontend.parser.struct.expression.Cond;
import frontend.parser.struct.expression.multiExp.LOrExp;

public class CondParser {
    public Cond parseCond() {
        LOrExpParser lorExpParser = new LOrExpParser();
        LOrExp lorExp = lorExpParser.parseLOrExp();
        return new Cond(lorExp);
    }
}
