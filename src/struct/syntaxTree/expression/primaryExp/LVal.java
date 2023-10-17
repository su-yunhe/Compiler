package struct.syntaxTree.expression.primaryExp;

import struct.token.Token;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.terminal.Ident;
import enums.SymbolType;

import java.util.ArrayList;

/**
 * LVal 左值表达式类
 * LVal → Ident {'[' Exp ']'} //1.普通变量 2.一维数组 3.二维数组
 * @author SYH
 * @date 2023/09/26
 */
public class LVal implements PrimaryExpEle {
    private final String name = "<LVal>";
    private Ident ident;
    private ArrayList<Token> leftBrackets;
    private ArrayList<Exp> exps;
    private ArrayList<Token> rightBrackets;

    public LVal(Ident ident,
                ArrayList<Token> leftBrackets,
                ArrayList<Exp> exps,
                ArrayList<Token> rightBrackets) {
        this.ident = ident;
        this.leftBrackets = leftBrackets;
        this.exps = exps;
        this.rightBrackets = rightBrackets;
    }

    public int getLineNum() {
        return ident.getLineNum();
    }

    public SymbolType getSymbolType() {
        if (ident.getSymbol() == null) {
            return null;
        }
        return ident.getSymbol().getSymbolType();
    }

    @Override
    public int getDimension() {
        if (ident.getSymbol() == null) {
            return Integer.MIN_VALUE;
        }
        return ident.getSymbol().getDimension() - leftBrackets.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        if (leftBrackets != null && exps != null && rightBrackets != null &&
                leftBrackets.size() == exps.size() && exps.size() == rightBrackets.size()) {
            int len = leftBrackets.size();
            for (int i = 0; i < len; i++) {
                sb.append(leftBrackets.get(i).toString());
                sb.append(exps.get(i).toString());
                sb.append(rightBrackets.get(i).toString());
            }
        }
        sb.append(name).append("\n");
        return sb.toString();
    }


}
