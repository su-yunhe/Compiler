package compiler.parser.expressionParser.multiExpParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.multiExp.EqExp;
import struct.syntaxTree.expression.multiExp.LAndExp;

import java.util.ArrayList;

public class LAndExpParser {
    /* LAndExp Attributes */
    private EqExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<EqExp> operands = new ArrayList<>();
    public LAndExp parseLAndExp() {
        operands = new ArrayList<>();
        operators = new ArrayList<>();
        EqExpParser eqExpParser = new EqExpParser();
        first = eqExpParser.parseEqExp();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.AND)) { // '&&'
            operators.add(token);
            operands.add(eqExpParser.parseEqExp());
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new LAndExp(first, operators, operands);
    }
}
