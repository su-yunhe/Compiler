package compiler.parser.expressionParser.primaryExpParser;

import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.ExpParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.primaryExp.PrimaryExpExp;

public class PrimaryExpExpParser {
    /* PrimaryExpExp Attribute */
    private Token leftParent = null; // '('
    private Exp exp = null;
    private Token rightParent = null; // ')'

    /**
     * PrimaryExpExp = '(' Exp ')'
     * @return {@link PrimaryExpExp}
     */
    public PrimaryExpExp parsePrimaryExpExp() {
        leftParent = TLIterator.readNext();
        exp = new ExpParser().parseExp();
        rightParent = TLIterator.readNext();
        return new PrimaryExpExp(leftParent, exp, rightParent);
    }
}
