package struct.syntaxTree.declaration.constant;

import struct.token.Token;
import struct.syntaxTree.declaration.constant.constInitVal.ConstInitVal;
import struct.syntaxTree.expression.ConstExp;
import struct.syntaxTree.terminal.Ident;

import java.util.ArrayList;

/**
 * ConstDef 常数定义类
 * ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal // 包含普通变量、一维数组、二维数组共三种情况
 * @author SYH
 * @date 2023/09/26
 */
public class ConstDef{
    private final String name = "<ConstDef>";
    private Ident ident;
    private ArrayList<Token> leftBracks; // '['
    private ArrayList<ConstExp> constExps;
    private ArrayList<Token> rightBrackets; // ']'
    private Token eq; // '='
    private ConstInitVal constInitval;

    public ConstDef(Ident ident,
                    ArrayList<Token> leftBracks,
                    ArrayList<ConstExp> constExps,
                    ArrayList<Token> rightBrackets,
                    Token eq,
                    ConstInitVal constInitval) {
        this.ident = ident;
        this.leftBracks = leftBracks;
        this.constExps = constExps;
        this.rightBrackets = rightBrackets;
        this.eq = eq;
        this.constInitval = constInitval;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ident.toString());
        if (this.leftBracks != null && this.rightBrackets != null && this.constExps != null &&
                this.leftBracks.size() == this.constExps.size() &&
                this.constExps.size() == this.rightBrackets.size()) {
            int len = leftBracks.size();
            for (int i = 0; i < len; i++) {
                sb.append(this.leftBracks.get(i).toString());
                sb.append(this.constExps.get(i).toString());
                sb.append(this.rightBrackets.get(i).toString());
            }
        }
        sb.append(this.eq.toString());
        sb.append(this.constInitval.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
