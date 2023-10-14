package struct.syntaxTree.declaration.variable.initVal;

/**
 * 变量初值类
 * InitVal → InitValEle = Exp | '{' [ InitVal { ',' InitVal } ] '}'// 1.表达式初值 2.一维数组初值 3.二维数组初值
 * @author SYH
 * @date 2023/09/26
 */
public class InitVal {
    private final String name = "<InitVal>";
    private InitValEle initValEle;

    public InitVal(InitValEle initValEle) {
        this.initValEle = initValEle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.initValEle.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
