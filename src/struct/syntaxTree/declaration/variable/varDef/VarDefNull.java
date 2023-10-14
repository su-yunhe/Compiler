package struct.syntaxTree.declaration.variable.varDef;

import struct.token.Token;
import struct.syntaxTree.expression.ConstExp;
import struct.syntaxTree.terminal.Ident;

import java.util.ArrayList;

/**
 * VarDefNull 无初始化的变量定义类
 *  VarDefNull = Ident { '[' ConstExp ']' }
 * @author SYH
 * @date 2023/09/26
 */
public class VarDefNull implements VarDefEle {
    private Ident ident;
    private ArrayList<Token> leftBraces;
    private ArrayList<ConstExp> constExps;
    private ArrayList<Token> rightBraces;

    public VarDefNull(Ident ident,
                      ArrayList<Token> leftBraces,
                      ArrayList<ConstExp> constExps,
                      ArrayList<Token> rightBraces) {
        this.ident = ident;
        this.leftBraces = leftBraces;
        this.constExps = constExps;
        this.rightBraces = rightBraces;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        if (this.leftBraces != null && this.constExps != null && this.rightBraces != null &&
                this.leftBraces.size() == this.constExps.size() &&
                this.constExps.size() == this.rightBraces.size()) {
            int len = this.leftBraces.size();
            for (int i = 0; i < len; i++) {
                sb.append(this.leftBraces.get(i).toString());
                sb.append(this.constExps.get(i).toString());
                sb.append(this.rightBraces.get(i).toString());
            }
        }
        return sb.toString();
    }
}
