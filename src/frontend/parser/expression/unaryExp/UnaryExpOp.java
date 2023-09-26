package frontend.parser.expression.unaryExp;

/**
 * UnaryExpOp 一元表达式单目运算符类
 * UnaryExpOp = UnaryOp UnaryExp
 * @author Lenovo
 * @date 2023/09/25
 */
public class UnaryExpOp implements UnaryExpEle{
    private UnaryOp unaryOp;
    private UnaryExp unaryExp;

    public UnaryExpOp(UnaryOp unaryOp, UnaryExp unaryExp) {
        this.unaryOp = unaryOp;
        this.unaryExp = unaryExp;
    }
}
