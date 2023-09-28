package frontend.parser.struct.expression.primaryExp;

import frontend.parser.struct.expression.unaryExp.UnaryExpEle;

/**
 * PrimaryExp 基本表达式类
 * PrimaryExp  -> PrimaryExpEle = '(' Exp ')' | LVal | Number
 * @author SYH
 * @since 2023/09/25
 */
public class PrimaryExp implements UnaryExpEle {
    /**
     * 语法类别名
     */
    private final String name = "<PrimaryExp>";
    /**
     * 基本表达式的文法基类
     */
    private PrimaryExpEle primaryExpEle;

    public PrimaryExp(PrimaryExpEle primaryExpEle) {
        this.primaryExpEle = primaryExpEle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.primaryExpEle.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }

}
