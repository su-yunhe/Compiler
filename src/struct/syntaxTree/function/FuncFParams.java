package struct.syntaxTree.function;

import struct.token.Token;
import struct.symbol.Symbol;

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

    public ArrayList<FuncFParam> getFuncFParamList() {
        return funcFParams;
    }

    public ArrayList<Symbol> getSymbols() {
        ArrayList<Symbol> symbols = new ArrayList<>();
        symbols.add(first.getSymbol());
        for (FuncFParam funcFParam: funcFParams) {
            symbols.add(funcFParam.getSymbol());
        }
        System.out.println("symbols");
        return symbols;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.first.toString());
        if (this.commas != null && this.funcFParams != null &&
                this.commas.size() == this.funcFParams.size()) {
            int len = this.commas.size();
            for (int i = 0; i < len; i++) {
                sb.append(this.commas.get(i).toString());
                sb.append(this.funcFParams.get(i).toString());
            }
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
