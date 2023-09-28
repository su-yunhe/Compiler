package frontend.parser.struct.expression.multiExp;

import frontend.lexer.Token;

import java.util.ArrayList;

/**
 * LAndExp 逻辑与表达式
 * LAndExp -> EqExp { '&&' EqExp }
 * @author SYH
 * @since 2023/09/25
 */
public class LAndExp extends AbstractMultiExp<EqExp>{
    public LAndExp(EqExp first, ArrayList<Token> operators, ArrayList<EqExp> operands) {
        super(first, operators, operands, "<LAndExp>");
    }
}
