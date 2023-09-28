package frontend.parser.parser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.functionParser.FuncFParamParser;
import frontend.parser.struct.function.FuncFParam;
import frontend.parser.struct.function.FuncFParams;

import java.util.ArrayList;

public class FuncFParamsParser {
    private TokenListIterator iterator;
    /* FunfFParams Attributes */
    private FuncFParam first = null;
    private ArrayList<Token> commas = new ArrayList<>();
    private ArrayList<FuncFParam> funcFParams = new ArrayList<>();

    public FuncFParamsParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * FuncFParams → FuncFParam { ',' FuncFParam } // 1.花括号内重复0次 2.花括号内重复多次
     * @return {@link FuncFParams}
     */
    public FuncFParams parseFuncFParams() {
        this.commas = new ArrayList<>();
        this.funcFParams = new ArrayList<>();
        /* FuncFParam */
        FuncFParamParser funcFParamParser = new FuncFParamParser(this.iterator);
        this.first = funcFParamParser.parseFuncFParam();
        Token token = this.iterator.readNextToken();
        while (token.getType().equals(LexType.COMMA)) {
            this.commas.add(token);
            this.funcFParams.add(funcFParamParser.parseFuncFParam());
            token = this.iterator.readNextToken();
        }
        this.iterator.unReadToken(1);
        return new FuncFParams(this.first, this.commas, this.funcFParams);
    }
}
