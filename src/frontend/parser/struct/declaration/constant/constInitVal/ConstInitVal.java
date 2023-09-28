package frontend.parser.struct.declaration.constant.constInitVal;

/**
 * ConstInitVal 常量初值类
 * ConstInitVal → ConstInitEle = ConstExp | '{' [ ConstInitVal { ',' ConstInitVal } ] '}'
 * @author SYH
 * @date 2023/09/26
 */
public class ConstInitVal {
    private final String name = "<ConstInitVal>";
    private ConstInitValEle constInitValEle;

    public ConstInitVal(ConstInitValEle constInitValEle) {
        this.constInitValEle = constInitValEle;
    }
}
