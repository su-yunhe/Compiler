package frontend.parser.struct.function;

import frontend.lexer.Token;
import frontend.parser.struct.declaration.BType;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

/**
 * FuncFParam 函数形参类
 * FuncFParam → BType Ident ['[' ']' { '[' ConstExp ']' }] // 1.普通变量2.一维 数组变量 3.二维数组变量
 * @author SYH
 * @date 2023/09/26
 */
public class FuncFParam {
    private final String name = "<FuncFParam>";
    private BType btype;
    private Ident ident;
    private Token leftBracketFirst = null; // '[' MAY exist
    private Token rightBracketFirst = null; // ']' MAY exist
    private ArrayList<Token> leftBrackets = null; // '[' MAY exist
    private ArrayList<ConstExp> constExps = null; // MAY exist
    private ArrayList<Token> rightBrackets = null; // ']' MAY exist

    public FuncFParam(BType btype,
                      Ident ident) {
        this.btype = btype;
        this.ident = ident;
    }

    public FuncFParam(BType btype,
                      Ident ident,
                      Token leftBracketFirst,
                      Token rightBrackFirst) {
        this(btype, ident);
        this.leftBracketFirst = leftBracketFirst;
        this.rightBracketFirst = rightBrackFirst;
    }

    public FuncFParam(BType btype,
                      Ident ident,
                      Token leftBracketFirst,
                      Token rightBrackFirst,
                      ArrayList<Token> leftBrackets,
                      ArrayList<ConstExp> constExps,
                      ArrayList<Token> rightBrackets) {
        this(btype, ident, leftBracketFirst, rightBrackFirst);
        this.leftBrackets = leftBrackets;
        this.constExps = constExps;
        this.rightBrackets = rightBrackets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.btype.toString());
        sb.append(this.ident.toString());
        if (this.leftBracketFirst != null && this.rightBracketFirst != null) {
            sb.append(this.leftBracketFirst.toString());
            sb.append(this.rightBracketFirst.toString());
            if (this.leftBrackets != null && this.constExps != null && this.rightBrackets != null &&
                    this.leftBrackets.size() == this.constExps.size() &&
                    this.constExps.size() == this.rightBrackets.size()) {
                int len = this.leftBrackets.size();
                for (int i = 0; i < len; i++) {
                    sb.append(this.leftBrackets.get(i).toString());
                    sb.append(this.constExps.get(i).toString());
                    sb.append(this.rightBrackets.get(i).toString());
                }
            }
        }
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
