package frontend.parser.struct.function;

import frontend.lexer.Token;

import java.util.ArrayList;

/**
 * FuncFParams 函数形参表类
 * FuncFParams → FuncFParam { ',' FuncFParam } // 1.花括号内重复0次 2.花括号内重复多次
 * @author SYH
 * @date 2023/09/26
 */
public class FuncFParams {
    private final String name = "<FuncFParams>";
    private FuncFParam first;
    private ArrayList<Token> commas = null; // ',' MAY exist
    private ArrayList<FuncFParam> funcFParams = null; // MAY exist

    public FuncFParams(FuncFParam first) {
        this.first = first;
    }

    public FuncFParams(FuncFParam first,
                       ArrayList<Token> commas,
                       ArrayList<FuncFParam> funcFParams) {
        this(first);
        this.commas = commas;
        this.funcFParams = funcFParams;
    }
}
