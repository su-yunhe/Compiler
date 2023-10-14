package compiler.parser.expressionParser;

import compiler.parser.expressionParser.multiExpParser.LOrExpParser;
import struct.syntaxTree.expression.Cond;
import struct.syntaxTree.expression.multiExp.LOrExp;

public class CondParser {
    public Cond parseCond() {
        LOrExpParser lorExpParser = new LOrExpParser();
        LOrExp lorExp = lorExpParser.parseLOrExp();
        return new Cond(lorExp);
    }
}
