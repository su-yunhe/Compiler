package frontend.parser.parser.expressionParser;

import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.multiExpParser.LOrExpParser;
import frontend.parser.struct.expression.Cond;
import frontend.parser.struct.expression.multiExp.LOrExp;

public class CondParser {
    private TokenListIterator iterator;

    public CondParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public Cond parseCond() {
        LOrExpParser lorExpParser = new LOrExpParser(this.iterator);
        LOrExp lorExp = lorExpParser.parseLOrExp();
        return new Cond(lorExp);
    }
}
