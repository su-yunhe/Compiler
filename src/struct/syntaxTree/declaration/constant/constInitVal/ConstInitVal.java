package struct.syntaxTree.declaration.constant.constInitVal;

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



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(constInitValEle.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
