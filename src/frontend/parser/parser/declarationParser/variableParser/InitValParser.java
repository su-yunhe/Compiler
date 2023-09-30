package frontend.parser.parser.declarationParser.variableParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.struct.declaration.variable.initVal.InitVal;
import frontend.parser.struct.declaration.variable.initVal.InitValEle;
import frontend.parser.struct.declaration.variable.initVal.InitValMulti;

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

        leftBrace = TLIterator.readNextToken();
        if (!leftBrace.getType().equals(LexType.LBRACE)) {
            /* Exp */
            TLIterator.unReadToken(1);
            initValEle = new ExpParser().parseExp();
        } else {
            /* '{' [ InitVal { ',' InitVal } ] '}' */
            Token token = TLIterator.readNextToken();
            if (!token.getType().equals(LexType.RBRACE)) {
                TLIterator.unReadToken(1);
                first = new InitValParser().parseInitVal();
                token = TLIterator.readNextToken();
                while (token.getType().equals(LexType.COMMA)) {
                    commas.add(token);
                    initVals.add(new InitValParser().parseInitVal());
                    token = TLIterator.readNextToken();
                }
            }
            rightBrace = token;
            initValEle = new InitValMulti(leftBrace, first, commas, initVals, rightBrace);
        }
        return new InitVal(initValEle);
    }
}
