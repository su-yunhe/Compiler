package compiler.parser.expressionParser.multiExpParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.multiExp.LAndExp;
import struct.syntaxTree.expression.multiExp.LOrExp;

import java.util.ArrayList;

public class LOrExpParser {
    /* LOrExp Attributes */
    private LAndExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<LAndExp> operands = new ArrayList<>();
    public LOrExp parseLOrExp() {
        operators = new ArrayList<>();
        operands = new ArrayList<>();

        first = new LAndExpParser().parseLAndExp();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.OR)) { // '||'
            operators.add(token);
            operands.add(new LAndExpParser().parseLAndExp());
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new LOrExp(first, operators, operands);
    }
}
