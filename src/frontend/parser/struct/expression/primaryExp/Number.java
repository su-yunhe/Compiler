package frontend.parser.struct.expression.primaryExp;

import frontend.parser.struct.terminal.IntConst;

/**
 * Number 数值类
 * Number → IntConst // 存在即可
 * @author SYH
 * @since 2023/09/25
 */
public class Number implements PrimaryExpEle{
    private final String name = "<Number>";
    private IntConst intConst;

    public Number(IntConst intConst) {
        this.intConst = intConst;
    }
    public IntConst getIntConst() {
        return intConst;
    }
}
