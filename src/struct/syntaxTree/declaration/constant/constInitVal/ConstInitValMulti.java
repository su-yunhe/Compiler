package struct.syntaxTree.declaration.constant.constInitVal;

import struct.token.Token;

import java.util.ArrayList;

/**
 * ConstInitValMulti 常量数组初值类
 * 包含 一维数组 和 二维数组
 * ConstInitVal -> '{' [ <ConstInitVal> { ',' <ConstInitVal> } ] '}'
 * @author SYH
 * @date 2023/09/26
 */
public class ConstInitValMulti implements ConstInitValEle{
    private Token leftBrace; // '{'
    private ConstInitVal first; // MAY exist
    private ArrayList<Token> commas; // MAY exist
    private ArrayList<ConstInitVal> constInitVals; // MAY exist
    private Token rightBrace; // '}'

    public ConstInitValMulti(Token leftBrace,
                             Token rightBrace) {
        this.leftBrace = leftBrace;
        this.rightBrace = rightBrace;
    }

    public ConstInitValMulti(Token leftBrace,
                             Token rightBrace,
                             ConstInitVal first) {
        this(leftBrace, rightBrace);
        this.first = first;
    }

    public ConstInitValMulti(Token leftBrace,
                             ConstInitVal first,
                             ArrayList<Token> commas,
                             ArrayList<ConstInitVal> constInitVals,
                             Token rightBrace) {
        this(leftBrace, rightBrace, first);
        this.commas = commas;
        this.constInitVals = constInitVals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.leftBrace.toString());
        if (this.first != null) {
            sb.append(this.first.toString());
            if (this.commas != null && this.constInitVals != null
                    && this.commas.size() == this.constInitVals.size()) {
                int len = this.commas.size();
                for (int i = 0; i < len; i++) {
                    sb.append(this.commas.get(i).toString());
                    sb.append(this.constInitVals.get(i).toString());
                }
            }
        }
        sb.append(this.rightBrace.toString());
        return sb.toString();
    }
}
