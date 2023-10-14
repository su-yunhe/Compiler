package struct.syntaxTree.expression.primaryExp;

import struct.token.Token;
import struct.syntaxTree.expression.Exp;

/**
 * PrimaryExpExp 基本表达式表达式类
 * PrimaryExpExp = '(' Exp ')'
 * @author SYH
 * @date 2023/09/26
 */
public class PrimaryExpExp implements PrimaryExpEle {
    private Token leftParent; // must be '('
    private Exp exp;
    private Token rightParent; // must be ')'

    public PrimaryExpExp(Token leftBracket, Exp exp, Token rightParent) {
        this.leftParent = leftBracket;
        this.exp = exp;
        this.rightParent = rightParent;
    }

    @Override
    public int getDimension() {
        return exp.getDimension();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(leftParent.toString());
        sb.append(exp.toString());
        sb.append(rightParent.toString());
        return sb.toString();
    }


}
