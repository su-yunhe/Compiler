package compiler.parser.expressionParser;

import compiler.parser.expressionParser.multiExpParser.AddExpParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.multiExp.AddExp;

public class ExpParser {
    private AddExp addExp;
    /**
     * Exp → AddExp
     * @return {@link Exp}
     */
    public Exp parseExp() {
        addExp = new AddExpParser().parseAddExp();
        return new Exp(addExp);
    }
}
