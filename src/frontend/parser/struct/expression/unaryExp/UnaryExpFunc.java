package frontend.parser.struct.expression.unaryExp;

import frontend.lexer.Token;
import frontend.parser.struct.expression.FuncRParams;
import frontend.parser.struct.terminal.Ident;

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
}
