package frontend.parser.parser.functionParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.declarationParser.constantParser.BTypeParser;
import frontend.parser.parser.expressionParser.ConstExpParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.declaration.BType;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.function.FuncFParam;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

public class FuncFParamParser {
    private TokenListIterator iterator;
    /* FuncFParam Attributes */
    private BType btype = null;
    private Ident ident = null;
    private Token leftBracketFirst = null;
    private Token rightBracketFirst = null;
    private ArrayList<Token> leftBrackets = new ArrayList<>();
    private ArrayList<ConstExp> constExps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();
    private FuncFParam funcFParam = null;

    public FuncFParamParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * FuncFParam → BType Ident ['[' ']' { '[' ConstExp ']' }] // 1.普通变量2.一维 数组变量 3.二维数组变量
     * @return {@link FuncFParam}
     */
    public FuncFParam parseFuncFParam() {
        this.leftBrackets = new ArrayList<>();
        this.constExps = new ArrayList<>();
        this.rightBrackets = new ArrayList<>();
        /* BType */
        BTypeParser btypeParser = new BTypeParser(this.iterator);
        this.btype = btypeParser.parseBtype();
        /* Ident */
        IdentParser identParser = new IdentParser(this.iterator);
        this.ident = identParser.parseIdent();
        this.leftBracketFirst = this.iterator.readNextToken();
        if (this.leftBracketFirst.getType().equals(LexType.LBRACK)) { // [
            /* 一维数组变量 和 二维数组变量 ['[' ']' { '[' ConstExp ']' }] */
            this.rightBracketFirst = this.iterator.readNextToken();
            Token token = this.iterator.readNextToken();
            while (token.getType().equals(LexType.LBRACK)) {
                this.leftBrackets.add(token);
                ConstExpParser constExpParser = new ConstExpParser(this.iterator);
                this.constExps.add(constExpParser.parseConstExp());
                this.rightBrackets.add(this.iterator.readNextToken());
                token = this.iterator.readNextToken();
            }
            this.iterator.unReadToken(1);
            this.funcFParam = new FuncFParam(this.btype, this.ident, this.leftBracketFirst,
                    this.rightBracketFirst, this.leftBrackets, this.constExps, this.rightBrackets);
        } else {
            /* 普通变量 */
            this.iterator.unReadToken(1);
            this.funcFParam = new FuncFParam(this.btype, this.ident);
        }
        return this.funcFParam;
    }
}
