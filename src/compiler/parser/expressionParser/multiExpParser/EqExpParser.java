package compiler.parser.expressionParser.multiExpParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.multiExp.EqExp;
import struct.syntaxTree.expression.multiExp.RelExp;

import java.util.ArrayList;

public class EqExpParser {
    /* EqExp Attributes */
    private RelExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<RelExp> operands = new ArrayList<>();
    public EqExp parseEqExp() {
        operands = new ArrayList<>();
        operators = new ArrayList<>();
        first = new RelExpParser().parseRelExp();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.EQL) || // '=='
                token.getType().equals(LexType.NEQ)) { // '!='
            operators.add(token);
            operands.add(new RelExpParser().parseRelExp());
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new EqExp(first, operators, operands);
    }
}
