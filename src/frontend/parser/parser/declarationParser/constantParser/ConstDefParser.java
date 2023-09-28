package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.ConstExpParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.declaration.constant.ConstDef;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitVal;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

public class ConstDefParser {
    private TokenListIterator iterator;
    /* ConstDef Attributes */
    private Ident ident;
    private ArrayList<Token> leftBrackets = new ArrayList<>();
    private ArrayList<ConstExp> constExps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();
    private Token eq; // =
    private ConstInitVal constInitVal;

    public ConstDefParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public ConstDef parseConstDef() {
        this.leftBrackets = new ArrayList<>();
        this.constExps = new ArrayList<>();
        this.rightBrackets = new ArrayList<>();
        IdentParser identParser = new IdentParser(this.iterator);
        ident = identParser.parseIdent();
        Token token = iterator.readNextToken();
        while (token.getType().equals(LexType.LBRACK)) {
            /* '[' */
            this.leftBrackets.add(token);
            /* ConstExp */
            ConstExpParser constExpParser = new ConstExpParser(this.iterator);
            ConstExp constExp = constExpParser.parseConstExp();
            this.constExps.add(constExp);
            token = this.iterator.readNextToken();
            /* ']' */
            if (!token.getType().equals(LexType.RBRACK)) {
                System.out.println("EXPECT RBRACK HERE");
            }
            this.rightBrackets.add(token);
            token = this.iterator.readNextToken();
        }
        if (!token.getType().equals(LexType.ASSIGN)) {
            System.out.println("EXPECT ASSIGN HERE");
        }
        this.eq = token;
        ConstInitValParser constInitValParser = new ConstInitValParser(this.iterator);
        this.constInitVal = constInitValParser.parseConstInitVal();
        return new ConstDef(this.ident, this.leftBrackets, this.constExps,
                this.rightBrackets, this.eq, this.constInitVal);
    }
}
