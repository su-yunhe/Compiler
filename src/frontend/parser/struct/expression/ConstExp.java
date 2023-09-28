package frontend.parser.struct.expression;

import frontend.parser.struct.declaration.constant.constInitVal.ConstInitValEle;
import frontend.parser.struct.expression.multiExp.AddExp;

/**
 * ConstExp 常量表达式
 * ConstExp → AddExp 注：使用的 Ident 必须是常量 // 存在即可
 * @author SYH
 * @since 2023/09/26
 */
public class ConstExp implements ConstInitValEle {
    private final String name = "<ConstExp>";
    private AddExp addExp;
    public ConstExp(AddExp addExp) {
        this.addExp = addExp;
    }
}