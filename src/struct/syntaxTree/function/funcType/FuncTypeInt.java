package struct.syntaxTree.function.funcType;

import enums.LexType;
import struct.token.Token;

/**
 * FuncInt int型函数类
 * FuncTypeInt = 'int'
 * @author SYH
 * @date 2023/09/26
 */
public class FuncTypeInt implements FuncTypeEle {
    private Token intTk; // 'int'

    public FuncTypeInt(Token intTk) {
        this.intTk = intTk;
    }

    @Override
    public LexType getType() {
        return intTk.getType();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.intTk.toString());
        return sb.toString();
    }


}
