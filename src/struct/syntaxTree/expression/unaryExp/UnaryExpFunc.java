package struct.syntaxTree.expression.unaryExp;

import struct.token.Token;
import struct.syntaxTree.expression.FuncRParams;
import struct.syntaxTree.terminal.Ident;

/**
 * UnaryExpFunc 一元表达式函数类
 * UnaryExpFunc = Ident '(' [FuncRParams] ')'
 * @author SYH
 * @since 2023/09/25
 */
public class UnaryExpFunc implements UnaryExpEle{
    /**
     * Ident
     */
    private Ident ident;
    /**
     * 左括号
     */
    private Token leftParent;
    /**
     * 函数实参表【可以为空】
     */
    private FuncRParams funcRParams = null; // MAY exist

    /**
     * 右括号
     */
    private Token rightParent;


    /**
     * 构造函数【无实参】
     * @param ident Ident
     * @param leftParent 左括号
     * @param rightParent 右括号
     */
    public UnaryExpFunc(Ident ident,
                        Token leftParent,
                        Token rightParent) {
        this.ident = ident;
        this.leftParent = leftParent;
        this.rightParent = rightParent;
    }

    public UnaryExpFunc(Ident ident,
                        FuncRParams funcRParams,
                        Token leftParent,
                        Token rightBracker) {
        this(ident, leftParent, rightBracker);
        this.funcRParams = funcRParams;
    }

    @Override
    public int getDimension() {
        if (ident.getSymbol() == null) {
            return Integer.MIN_VALUE;
        }
        return ident.getSymbol().getDimension();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ident.toString());
        sb.append(leftParent.toString());
        if (funcRParams != null) {
            sb.append(this.funcRParams.toString());
        }
        sb.append(rightParent.toString());
        return sb.toString();
    }


}
