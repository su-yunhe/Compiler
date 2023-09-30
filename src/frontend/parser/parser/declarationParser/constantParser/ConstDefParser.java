package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ConstExpParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.declaration.constant.ConstDef;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitVal;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

public class ConstDefParser {
    private Ident ident;
    private ArrayList<Token> leftBrackets = new ArrayList<>();
    private ArrayList<ConstExp> constExps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();
    private Token eq; // =
    private ConstInitVal constInitVal;

    /**
     * ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal
     * @return {@link ConstDef}
     */
    public ConstDef parseConstDef() {
        leftBrackets = new ArrayList<>();
        constExps = new ArrayList<>();
        rightBrackets = new ArrayList<>();
        ident = new IdentParser().parseIdent();
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.LBRACK)) {
            /* '[' */
            leftBrackets.add(token);
            /* ConstExp */
            ConstExp constExp = new ConstExpParser().parseConstExp();
            constExps.add(constExp);
            token = TLIterator.readNextToken();
            /* ']' */
            if (!token.getType().equals(LexType.RBRACK)) {
                System.out.println("EXPECT RBRACK HERE");
            }
            rightBrackets.add(token);
            token = TLIterator.readNextToken();
        }
        /* = */
        if (!token.getType().equals(LexType.ASSIGN)) {
            System.out.println("EXPECT ASSIGN HERE");
        }
        eq = token;
        this.constInitVal = new ConstInitValParser().parseConstInitVal();
        return new ConstDef(ident, leftBrackets, constExps, rightBrackets, eq, constInitVal);
    }
}
