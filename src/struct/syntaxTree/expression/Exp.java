package struct.syntaxTree.expression;

import struct.syntaxTree.declaration.variable.initVal.InitValEle;
import struct.syntaxTree.expression.multiExp.AddExp;

/**
 * Exp 表达式类
 * Exp → AddExp 注：SysY 表达式是int 型表达式 // 存在即可
 * @author SYH
 * @since 2023/09/26
 */
public class Exp implements InitValEle {
    private final String name = "<Exp>";
    private AddExp addExp;

    public Exp(AddExp addExp) {
        this.addExp = addExp;
    }

    public AddExp getAddExp() {
        return addExp;
    }

    public int getDimension() {
        return addExp.getDimension();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.addExp.toString());
        sb.append(this.name + "\n");
        return sb.toString();
    }
}
