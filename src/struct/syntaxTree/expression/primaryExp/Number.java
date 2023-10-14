package struct.syntaxTree.expression.primaryExp;

import struct.syntaxTree.terminal.IntConst;

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

    @Override
    public int getDimension() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(intConst.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }


}
