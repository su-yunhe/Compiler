package frontend.parser.struct.expression.unaryExp;

/**
 * UnaryExpEle 一元表达式类文法基类接口
 * UnaryExpEle = PrimaryExp | UnaryExpFunc = Ident '(' [FuncRParams] ')' | UnaryExpOp = UnaryOp UnaryExp
 * 被 PrimaryExp、UnaryExpFunc 和 UnaryExpOp 继承
 * @author SYH
 * @since 2023/09/25
 */
public interface UnaryExpEle {
}
