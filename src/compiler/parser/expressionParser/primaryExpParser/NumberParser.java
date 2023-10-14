package compiler.parser.expressionParser.primaryExpParser;

import compiler.parser.terminalParser.IntConstParser;
import struct.syntaxTree.terminal.IntConst;
import struct.syntaxTree.expression.primaryExp.Number;
public class NumberParser {
    public Number parseNumber() {
        IntConst intConst = new IntConstParser().parseIntConst();
        return new Number(intConst);
    }
}
