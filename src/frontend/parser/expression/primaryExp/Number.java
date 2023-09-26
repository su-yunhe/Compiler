package frontend.parser.expression.primaryExp;

import frontend.parser.terminal.IntConst;

/**
 * Number 数值类
 * Number → IntConst // 存在即可
 * @author SYH
 * @since 2023/09/25
 */
public class Number {
    private final String name = "<Number>";
    private IntConst intConst;

    public Number(IntConst intConst) {
        this.intConst = intConst;
    }
    public IntConst getIntConst() {
        return intConst;
    }
}
