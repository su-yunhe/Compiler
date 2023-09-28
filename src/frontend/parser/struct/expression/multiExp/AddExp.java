package frontend.parser.struct.expression.multiExp;

import frontend.lexer.Token;

import java.util.ArrayList;

/**
 * AddExp 加减表达式类
 * AddExp -> MulExp { ('+' | '-') MulExp }
 * @author SYH
 * @date 2023/09/26
 */
public class AddExp extends AbstractMultiExp<MulExp> {
    public AddExp(MulExp first, ArrayList<Token> tokens, ArrayList<MulExp> operands) {
        super(first, tokens, operands, "<AddExp>");
    }


}
