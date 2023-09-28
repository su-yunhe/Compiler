package frontend.parser.struct.function.funcType;

import frontend.lexer.Token;

/** FuncTypeVoid void型函数类
 * FuncTypeVoid = 'void'
 * @author SYH
 * @date 2023/09/26
 */
public class FuncTypeVoid implements FuncTypeEle {
    private Token voidTk; // 'void'

    public FuncTypeVoid(Token voidTk) {
        this.voidTk = voidTk;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.voidTk.toString());
        return sb.toString();
    }
}