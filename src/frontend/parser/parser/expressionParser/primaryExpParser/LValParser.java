package frontend.parser.parser.expressionParser.primaryExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.LVal;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

public class LValParser {
    /* LVal Attributes */
    private Ident ident = null;
    private ArrayList<Token> leftBrackets = new ArrayList<>(); // '['
    private ArrayList<Exp> exps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();

    /**
     * LVal → Ident {'[' Exp ']'}
     * @return {@link LVal}
     */
    public LVal parseLVal() {
        leftBrackets = new ArrayList<>();
        exps = new ArrayList<>();
        rightBrackets = new ArrayList<>();

        ident = new IdentParser().parseIdent();
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.LBRACK)) { // '['
            leftBrackets.add(token);
            exps.add(new ExpParser().parseExp());
            token = TLIterator.readNextToken(); // ']'
            rightBrackets.add(token);
            token = TLIterator.readNextToken();
        }
        TLIterator.unReadToken(1);
        return new LVal(ident, leftBrackets, exps, rightBrackets);
    }
}
