package compiler.parser.declarationParser.constantParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.declaration.constant.constInitVal.ConstInitVal;
import struct.syntaxTree.declaration.constant.constInitVal.ConstInitValMulti;

import java.util.ArrayList;

public class ConstInitValMultiParser {
    /* ConstInitMulti Attributes */
    private Token leftBrace = null; // '{'
    private ConstInitVal first = null; // MAY exist
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

        leftBrace = TLIterator.readNext();
        if (!leftBrace.getType().equals(LexType.LBRACE)) {
            System.out.println("EXPECT LBRACE HERE");
        }
        Token token = TLIterator.readNext();
        if (!token.getType().equals(LexType.RBRACE)) {
            TLIterator.unRead(1);
            first = new ConstInitValParser().parseConstInitVal();
            token = TLIterator.readNext();
            while (token.getType().equals(LexType.COMMA)) { // ','
                commas.add(token);
                constInitVals.add(new ConstInitValParser().parseConstInitVal());
                token = TLIterator.readNext();
            }
            TLIterator.unRead(1);
        } else {
            TLIterator.unRead(1);
        }
        rightBrace = TLIterator.readNext();
        return new ConstInitValMulti(leftBrace, first, commas, constInitVals, rightBrace);
    }
}
