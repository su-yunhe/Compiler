package frontend.parser.struct.expression.multiExp;

import frontend.lexer.Token;

import java.util.ArrayList;

/**
 * AbstractMultiExp 二元表达式抽象类
 * 由 MulExp, AddExp, RelExp, EqExp, LAndExp, LOrExp 继承
 * @author SYH
 * @since 2023/09/25
 */
public abstract class AbstractMultiExp<T>  {
    /**
     * 首字符
     */
    private T first;
    /**
     * 操作符
     */
    private ArrayList<Token> operators;
    /**
     * 操作数(也是二元表达式)
     */
    private ArrayList<T> operands;
    /**
     * 语法类别名
     */
    private String name;

    /**
     * 超类构造方法
     * @param first 首字符
     * @param operators 操作符
     * @param operands 操作数(也是二元表达式)
     * @param name 语法类别名
     */
    public AbstractMultiExp(T first, ArrayList<Token> operators, ArrayList<T> operands, String name) {
        this.first = first;
        this.operators = operators;
        this.operands = operands;
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(first.toString());
        sb.append(this.name).append("\n");
        if (operators != null && operands != null && operators.size() == operands.size()) {
            int len = operators.size();
            for (int i = 0; i < len; i++) {
                sb.append(operators.get(i).toString());
                sb.append(operands.get(i).toString());
                sb.append(this.name).append("\n");
            }
        }
        return sb.toString();
    }
}
