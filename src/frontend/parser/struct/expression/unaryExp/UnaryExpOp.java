package frontend.parser.struct.expression.unaryExp;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.unaryOp.toString());
        sb.append(this.unaryExp.toString());
        return sb.toString();
    }
}
