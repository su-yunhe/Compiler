package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitVal;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitValMulti;

import java.util.ArrayList;

public class ConstInitValMultiParser {
    private TokenListIterator iterator;
    /* ConstInitMulti Attributes */
    private Token leftBrace = null; // '{'
    private ConstInitVal first; // MAY exist
    private ArrayList<Token> commas = new ArrayList<>(); // MAY exist
    private ArrayList<ConstInitVal> constInitVals = new ArrayList<>(); // MAY exist
    private Token rightBrace = null; // '}'

    public ConstInitValMultiParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public ConstInitValMulti parseConstInitValMulti() {
        this.commas = new ArrayList<>();
        this.constInitVals = new ArrayList<>();
        this.leftBrace = this.iterator.readNextToken();
        if (!this.leftBrace.getType().equals(LexType.LBRACE)) {
            System.out.println("EXPECT LBRACE HERE");
        }
        Token token = this.iterator.readNextToken();
        if (!token.getType().equals(LexType.RBRACE)) {
            this.iterator.unReadToken(1);
            ConstInitValParser constInitValParser = new ConstInitValParser(this.iterator);
            this.first = constInitValParser.parseConstInitVal();
            token = this.iterator.readNextToken();
            while (token.getType().equals(LexType.COMMA)) { // ','
                this.commas.add(token);
                this.constInitVals.add(constInitValParser.parseConstInitVal());
                token = this.iterator.readNextToken();
            }
            this.iterator.unReadToken(1);
        } else {
            this.iterator.unReadToken(1);
        }
        this.rightBrace = this.iterator.readNextToken();
        return new ConstInitValMulti(this.leftBrace,
                this.first, this.commas, this.constInitVals, this.rightBrace);
    }
}
