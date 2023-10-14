package compiler.parser.expressionParser.multiExpParser;

import eumes.LexType;
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
        operands = new ArrayList<>();
        operators = new ArrayList<>();
        LAndExpParser landExpParser = new LAndExpParser();
        first = landExpParser.parseLAndExp();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.OR)) { // '||'
            operators.add(token);
            operands.add(landExpParser.parseLAndExp());
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new LOrExp(first, operators, operands);
    }
}
