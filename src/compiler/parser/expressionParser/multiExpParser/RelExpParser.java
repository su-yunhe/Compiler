package compiler.parser.expressionParser.multiExpParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.multiExp.AddExp;
import struct.syntaxTree.expression.multiExp.RelExp;

import java.util.ArrayList;

public class RelExpParser {
    /* RelExp Attributes */
    private AddExp first = null;
    private ArrayList<Token> operators = new ArrayList<>();
    private ArrayList<AddExp> operands = new ArrayList<>();
    public RelExp parseRelExp() {
        operands = new ArrayList<>();
        operators = new ArrayList<>();
        AddExpParser addExpParser = new AddExpParser();
        first = addExpParser.parseAddExp();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.LSS) || // <
                token.getType().equals(LexType.GRE) || // >
                token.getType().equals(LexType.LEQ) || // <=
                token.getType().equals(LexType.GEQ)) { // >=
            operators.add(token);
            operands.add(addExpParser.parseAddExp());
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new RelExp(first, operators, operands);
    }
}
