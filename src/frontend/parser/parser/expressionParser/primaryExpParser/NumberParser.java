package frontend.parser.parser.expressionParser.primaryExpParser;

import frontend.lexer.TokenListIterator;
import frontend.parser.parser.terminalParser.IntConstParser;
import frontend.parser.struct.terminal.IntConst;
import frontend.parser.struct.expression.primaryExp.Number;
public class NumberParser {
    private TokenListIterator iterator;

    public NumberParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public Number parseNumber() {
        IntConstParser intConstParser = new IntConstParser(this.iterator);
        IntConst intConst = intConstParser.parseIntConst();
        return new Number(intConst);
    }
}
