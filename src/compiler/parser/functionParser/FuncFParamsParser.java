package compiler.parser.functionParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.function.FuncFParam;
import struct.syntaxTree.function.FuncFParams;

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
        commas = new ArrayList<>();
        funcFParams = new ArrayList<>();
        /* FuncFParam */
        FuncFParamParser funcFParamParser = new FuncFParamParser();
        first = funcFParamParser.parseFuncFParam();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.COMMA)) {
            commas.add(token);
            funcFParams.add(funcFParamParser.parseFuncFParam());
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new FuncFParams(first, commas, funcFParams);
    }
}
