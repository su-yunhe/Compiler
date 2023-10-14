package struct.syntaxTree.expression.multiExp;

import struct.token.Token;

import java.util.ArrayList;

/**
 * EqExp 相等性表达式
 * EqExp -> RelExp { ('==' | '!=') RelExp }
 * @author SYH
 * @date 2023/09/26
 */
public class EqExp extends ASTMultiExp<RelExp> {
    public EqExp(RelExp first, ArrayList<Token> operators,
                 ArrayList<RelExp> operands) {
        super(first, operators, operands, "<EqExp>");
    }
}
