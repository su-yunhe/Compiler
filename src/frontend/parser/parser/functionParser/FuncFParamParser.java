package frontend.parser.parser.functionParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.declarationParser.constantParser.BTypeParser;
import frontend.parser.parser.expressionParser.ConstExpParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.declaration.BType;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.function.FuncFParam;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

public class FuncFParamParser {
    /* FuncFParam Attributes */
    private BType btype = null;
    private Ident ident = null;
    private Token leftBracketFirst = null;
    private Token rightBracketFirst = null;
    private ArrayList<Token> leftBrackets = new ArrayList<>();
    private ArrayList<ConstExp> constExps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();
    private FuncFParam funcFParam = null;

    /**
     * FuncFParam → BType Ident ['[' ']' { '[' ConstExp ']' }] // 1.普通变量2.一维 数组变量 3.二维数组变量
     * @return {@link FuncFParam}
     */
    public FuncFParam parseFuncFParam() {
        leftBrackets = new ArrayList<>();
        constExps = new ArrayList<>();
        rightBrackets = new ArrayList<>();
        /* BType */
        btype = new BTypeParser().parseBtype();
        /* Ident */
        ident = new IdentParser().parseIdent();
        leftBracketFirst = TLIterator.readNextToken();
        if (leftBracketFirst.getType().equals(LexType.LBRACK)) { // [
            /* 一维数组变量 和 二维数组变量 ['[' ']' { '[' ConstExp ']' }] */
            rightBracketFirst = TLIterator.readNextToken();
            Token token = TLIterator.readNextToken();
            while (token.getType().equals(LexType.LBRACK)) {
                leftBrackets.add(token);
                constExps.add(new ConstExpParser().parseConstExp());
                rightBrackets.add(TLIterator.readNextToken());
                token = TLIterator.readNextToken();
            }
            TLIterator.unReadToken(1);
            funcFParam = new FuncFParam(btype, ident, leftBracketFirst, rightBracketFirst, leftBrackets, constExps, rightBrackets);
        } else {
            /* 普通变量 */
            TLIterator.unReadToken(1);
            funcFParam = new FuncFParam(btype, ident);
        }
        return funcFParam;
    }
}
