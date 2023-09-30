package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitVal;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitValMulti;

import java.util.ArrayList;

public class ConstInitValMultiParser {
    /* ConstInitMulti Attributes */
    private Token leftBrace = null; // '{'
    private ConstInitVal first; // MAY exist
    private ArrayList<Token> commas = new ArrayList<>(); // MAY exist
    private ArrayList<ConstInitVal> constInitVals = new ArrayList<>(); // MAY exist
    private Token rightBrace = null; // '}'

    /**
     * ConstInitValMulti -> '{' [ <ConstInitVal> { ',' <ConstInitVal> } ] '}'
     * @return {@link ConstInitValMulti}
     */
    public ConstInitValMulti parseConstInitValMulti() {
        commas = new ArrayList<>();
        constInitVals = new ArrayList<>();

        leftBrace = TLIterator.readNextToken();
        if (!leftBrace.getType().equals(LexType.LBRACE)) {
            System.out.println("EXPECT LBRACE HERE");
        }
        Token token = TLIterator.readNextToken();
        if (!token.getType().equals(LexType.RBRACE)) {
            TLIterator.unReadToken(1);
            first = new ConstInitValParser().parseConstInitVal();
            token = TLIterator.readNextToken();
            while (token.getType().equals(LexType.COMMA)) { // ','
                commas.add(token);
                constInitVals.add(new ConstInitValParser().parseConstInitVal());
                token = TLIterator.readNextToken();
            }
            TLIterator.unReadToken(1);
        } else {
            TLIterator.unReadToken(1);
        }
        rightBrace = TLIterator.readNextToken();
        return new ConstInitValMulti(leftBrace, first, this.commas, constInitVals, rightBrace);
    }
}
