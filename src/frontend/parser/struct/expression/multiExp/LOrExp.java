package frontend.parser.struct.expression.multiExp;

import frontend.lexer.Token;

import java.util.ArrayList;

/**
 * LOrExp 逻辑或表达式
 * LOrExp -> LAndExp { '||' LAndExp }
 * @author SYH
 * @date 2023/09/25
 */
public class LOrExp extends AbstractMultiExp<LAndExp>{
    public LOrExp(LAndExp first, ArrayList<Token> operators, ArrayList<LAndExp> operands) {
        super(first, operators, operands, "<LOrExp>");
    }
}
