package struct.syntaxTree.expression.unaryExp;

/**
 * UnaryExp 一元表达式类
 * UnaryExp → UnaryExpEle = PrimaryExp | Ident '(' [FuncRParams] ')' // 3种情况均需覆盖,函数调用也需要覆盖FuncRParams的不同情况
 * @author SYH
 * @date 2023/09/26
 */
public class UnaryExp {
    /**
     * 语法类别名
     */
    private final String name = "<UnaryExp>";
    /**
     * UnaryExp 所包含的所有文法
     */
    private UnaryExpEle unaryExpEle;

    /**
     * @param unaryExpEle 一元表达式的文法
     */
    public UnaryExp(UnaryExpEle unaryExpEle) {
        this.unaryExpEle = unaryExpEle;
    }

    public int getDimension() {
        return this.unaryExpEle.getDimension();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.unaryExpEle.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
