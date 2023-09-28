package frontend.parser.parser.declarationParser.variableParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.struct.declaration.variable.initVal.InitVal;
import frontend.parser.struct.declaration.variable.initVal.InitValEle;
import frontend.parser.struct.declaration.variable.initVal.InitValMulti;

import java.util.ArrayList;

public class InitValParser {
    private TokenListIterator iterator;
    /* InitVal Attributes */
    private Token leftBrace = null; // '{'
    private InitVal first = null;
    private ArrayList<Token> commas = new ArrayList<>(); // ','
    private ArrayList<InitVal> initVals = new ArrayList<>();
    private Token rightBrace; // '}'
    private InitValEle initValEle = null;

    public InitValParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * InitVal → ExpEle = Exp | '{' [ InitVal { ',' InitVal } ] '}'
     * @return {@link InitVal}
     */
    public InitVal parseInitVal() {
        this.commas = new ArrayList<>();
        this.initVals = new ArrayList<>();
        this.leftBrace = this.iterator.readNextToken();
        if (!this.leftBrace.getType().equals(LexType.LBRACE)) {
            /* Exp */
            this.iterator.unReadToken(1);
            ExpParser expParser = new ExpParser(this.iterator);
            this.initValEle = expParser.parseExp();
        } else {
            /* '{' [ InitVal { ',' InitVal } ] '}' */
            Token token = this.iterator.readNextToken();
            if (!token.getType().equals(LexType.RBRACE)) {
                this.iterator.unReadToken(1);
                InitValParser initValParser = new InitValParser(this.iterator);
                this.first = initValParser.parseInitVal();
                token = this.iterator.readNextToken();
                while (token.getType().equals(LexType.COMMA)) {
                    this.commas.add(token);
                    this.initVals.add(initValParser.parseInitVal());
                    token = this.iterator.readNextToken();
                }
            }
            this.rightBrace = token;
            initValEle = new InitValMulti(this.leftBrace, this.first, this.commas,
                    this.initVals, this.rightBrace);
        }
        return new InitVal(this.initValEle);
    }
}
