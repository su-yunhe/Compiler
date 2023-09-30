package frontend.parser.parser.functionParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.functionParser.FuncFParamParser;
import frontend.parser.struct.function.FuncFParam;
import frontend.parser.struct.function.FuncFParams;

import java.util.ArrayList;

public class FuncFParamsParser {
    /* FunfFParams Attributes */
    private FuncFParam first = null;
    private ArrayList<Token> commas = new ArrayList<>();
    private ArrayList<FuncFParam> funcFParams = new ArrayList<>();
    /**
     * FuncFParams → FuncFParam { ',' FuncFParam } // 1.花括号内重复0次 2.花括号内重复多次
     * @return {@link FuncFParams}
     */
    public FuncFParams parseFuncFParams() {
        this.commas = new ArrayList<>();
        this.funcFParams = new ArrayList<>();
        /* FuncFParam */
        FuncFParamParser funcFParamParser = new FuncFParamParser();
        this.first = funcFParamParser.parseFuncFParam();
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.COMMA)) {
            this.commas.add(token);
            this.funcFParams.add(funcFParamParser.parseFuncFParam());
            token = TLIterator.readNextToken();
        }
        TLIterator.unReadToken(1);
        return new FuncFParams(this.first, this.commas, this.funcFParams);
    }
}
