package compiler.parser.declarationParser.variableParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.ExpParser;
import struct.syntaxTree.declaration.variable.initVal.InitVal;
import struct.syntaxTree.declaration.variable.initVal.InitValEle;
import struct.syntaxTree.declaration.variable.initVal.InitValMulti;

import java.util.ArrayList;

public class InitValParser {
    /* InitVal Attributes */
    private Token leftBrace = null; // '{'
    private InitVal first = null;
    private ArrayList<Token> commas = new ArrayList<>(); // ','
    private ArrayList<InitVal> initVals = new ArrayList<>();
    private Token rightBrace; // '}'
    private InitValEle initValEle = null;

    /**
     * InitVal → ExpEle = Exp | '{' [ InitVal { ',' InitVal } ] '}'
     * @return {@link InitVal}
     */
    public InitVal parseInitVal() {
        commas = new ArrayList<>();
        initVals = new ArrayList<>();

        leftBrace = TLIterator.readNext();
        if (!leftBrace.getType().equals(LexType.LBRACE)) {
            /* Exp */
            TLIterator.unRead(1);
            initValEle = new ExpParser().parseExp();
        } else {
            /* '{' [ InitVal { ',' InitVal } ] '}' */
            Token token = TLIterator.readNext();
            if (!token.getType().equals(LexType.RBRACE)) {
                TLIterator.unRead(1);
                first = new InitValParser().parseInitVal();
                token = TLIterator.readNext();
                while (token.getType().equals(LexType.COMMA)) {
                    commas.add(token);
                    initVals.add(new InitValParser().parseInitVal());
                    token = TLIterator.readNext();
                }
            }
            rightBrace = token;
            initValEle = new InitValMulti(leftBrace, first, commas, initVals, rightBrace);
        }
        return new InitVal(initValEle);
    }
}
