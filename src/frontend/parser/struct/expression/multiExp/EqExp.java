package frontend.parser.struct.expression.multiExp;

import frontend.lexer.Token;

import java.util.ArrayList;

/**
 * EqExp 相等性表达式
 * EqExp -> RelExp { ('==' | '!=') RelExp }
 * @author SYH
 * @date 2023/09/26
 */
public class EqExp extends AbstractMultiExp<RelExp> {
    public EqExp(RelExp first, ArrayList<Token> operators,
                 ArrayList<RelExp> operands) {
        super(first, operators, operands, "<EqExp>");
    }
}
