package frontend.parser.expression;

import frontend.parser.expression.multiExp.LOrExp;

/**
 * Cond 条件表达式类
 * Cond → LOrExp
 * @author SYH
 * @since 2023/09/26
 */
public class Cond {
    private final String name = "<Cond>";
    private LOrExp lOrExp;

    public Cond(LOrExp lOrExp) {
        this.lOrExp = lOrExp;
    }
}
