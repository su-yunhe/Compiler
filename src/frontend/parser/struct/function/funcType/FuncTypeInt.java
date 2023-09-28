package frontend.parser.struct.function.funcType;

import frontend.lexer.Token;

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
}
