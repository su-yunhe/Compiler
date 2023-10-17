package compiler.parser.expressionParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.FuncRParams;

import java.util.ArrayList;

public class FuncRParamsParser {
    /* FuncRParams Attributes */
    private Exp first = null;
    private ArrayList<Token> commas = new ArrayList<>();
    private ArrayList<Exp> exps = new ArrayList<>();
    /**
     * FuncRParams → Exp { ',' Exp }
     * @return {@link FuncRParams}
     */
    public FuncRParams parseFuncRParams() {
        commas = new ArrayList<>();
        exps = new ArrayList<>();

        first = new ExpParser().parseExp();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.COMMA)) { // ','
            commas.add(token);
            exps.add(new ExpParser().parseExp());
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new FuncRParams(first, commas, exps);
    }
}
