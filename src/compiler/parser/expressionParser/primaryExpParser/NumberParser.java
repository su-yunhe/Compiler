package compiler.parser.expressionParser.primaryExpParser;

import compiler.parser.terminalParser.IntConstParser;
import struct.syntaxTree.terminal.IntConst;
import struct.syntaxTree.expression.primaryExp.Number;
public class NumberParser {
    private IntConst intConst = null;
    public Number parseNumber() {
        intConst = new IntConstParser().parseIntConst();
        return new Number(intConst);
    }
}
