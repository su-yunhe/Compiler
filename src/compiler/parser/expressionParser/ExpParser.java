package compiler.parser.expressionParser;

import compiler.parser.expressionParser.multiExpParser.AddExpParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.multiExp.AddExp;

public class ExpParser {
    /**
     * Exp → AddExp
     * @return {@link Exp}
     */
    public Exp parseExp() {
        AddExp addExp = new AddExpParser().parseAddExp();
        return new Exp(addExp);
    }
}
