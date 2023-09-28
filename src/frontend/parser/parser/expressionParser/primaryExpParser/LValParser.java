package frontend.parser.parser.expressionParser.primaryExpParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.expression.primaryExp.LVal;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

public class LValParser {
    private TokenListIterator iterator;
    /* LVal Attributes */
    private Ident ident = null;
    private ArrayList<Token> leftBrackets = new ArrayList<>(); // '['
    private ArrayList<Exp> exps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();

    public LValParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public LVal parseLVal() {
        this.leftBrackets = new ArrayList<>();
        this.exps = new ArrayList<>();
        this.rightBrackets = new ArrayList<>();
        IdentParser identParser = new IdentParser(this.iterator);
        this.ident = identParser.parseIdent();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.LBRACK)) { // '['
            this.leftBrackets.add(token);
            ExpParser expParser = new ExpParser(this.iterator);
            this.exps.add(expParser.parseExp());
            token = this.iterator.readNextToken(); // ']'
            this.rightBrackets.add(token);
            token = this.iterator.readNextToken();
        }
        this.iterator.unReadToken(1);
        return new LVal(this.ident, this.leftBrackets, this.exps, this.rightBrackets);
    }
}
