package frontend.parser.expression.multiExp;

import frontend.lexer.Token;
import frontend.parser.expression.unaryExp.UnaryExp;

import java.util.ArrayList;

/**
 * MulExp 乘除模表达式
 * MulExp -> UnaryExp { ('*' | '/' | '%') UnaryExp }
 * @author SYH
 * @date 2023/09/26
 */
public class MulExp extends AbstractMultiExp<UnaryExp>{
    public MulExp(UnaryExp first, ArrayList<Token> operators, ArrayList<UnaryExp> operands) {
        super(first, operators, operands, "<MulExp>");
    }
}
