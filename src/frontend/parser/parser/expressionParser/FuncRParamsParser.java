package frontend.parser.parser.expressionParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.FuncRParams;

import java.util.ArrayList;

public class FuncRParamsParser {
    private TokenListIterator iterator;
    /* FuncRParams Attributes */
    private Exp first = null;
    private ArrayList<Token> commas = new ArrayList<>();
    private ArrayList<Exp> exps = new ArrayList<>();

    public FuncRParamsParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * FuncRParams → Exp { ',' Exp }
     * @return {@link FuncRParams}
     */
    public FuncRParams parseFuncRParams() {
        this.commas = new ArrayList<>();
        this.exps = new ArrayList<>();
        ExpParser expParser = new ExpParser(this.iterator);
        first = expParser.parseExp();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.COMMA)) { // ','
            this.commas.add(token);
            this.exps.add(expParser.parseExp());
            token = this.iterator.readNextToken();
        }
        this.iterator.unReadToken(1);
        return new FuncRParams(this.first, this.commas, this.exps);
    }
}
