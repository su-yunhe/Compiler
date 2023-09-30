package frontend.parser.parser.expressionParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.FuncRParams;

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
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.COMMA)) { // ','
            commas.add(token);
            exps.add(new ExpParser().parseExp());
            token = TLIterator.readNextToken();
        }
        TLIterator.unReadToken(1);
        return new FuncRParams(first, commas, exps);
    }
}
