package frontend.parser.parser.expressionParser.primaryExpParser;

import frontend.parser.TLIterator;
import frontend.parser.parser.terminalParser.IntConstParser;
import frontend.parser.struct.terminal.IntConst;
import frontend.parser.struct.expression.primaryExp.Number;
public class NumberParser {
    public Number parseNumber() {
        IntConst intConst = new IntConstParser().parseIntConst();
        return new Number(intConst);
    }
}
