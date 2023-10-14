package compiler.parser.expressionParser;

import compiler.parser.expressionParser.multiExpParser.AddExpParser;
import struct.syntaxTree.expression.ConstExp;
import struct.syntaxTree.expression.multiExp.AddExp;

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
