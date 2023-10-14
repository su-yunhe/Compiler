package struct.syntaxTree.expression;

import struct.syntaxTree.expression.multiExp.LOrExp;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(lOrExp.toString());
        sb.append(this.name + "\n");
        return sb.toString();
    }
}
