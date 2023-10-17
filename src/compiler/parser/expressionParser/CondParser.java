package compiler.parser.expressionParser;

import compiler.parser.expressionParser.multiExpParser.LOrExpParser;
import struct.syntaxTree.expression.Cond;
import struct.syntaxTree.expression.multiExp.LOrExp;

public class CondParser {
    private LOrExp lorExp = null;
    public Cond parseCond() {
        lorExp = new LOrExpParser().parseLOrExp();
        return new Cond(lorExp);
    }
}
