package frontend.parser.struct.expression.multiExp;

import frontend.lexer.Token;

import java.util.ArrayList;

/**
 * RelExp 关系表达式
 * RelExp -> AddExp { ('<' | '>' | '<=' | '>=') AddExp }
 * @author SYH
 * @date 2023/09/26
 */
public class RelExp extends AbstractMultiExp<AddExp> {
    public RelExp(AddExp first, ArrayList<Token> operators,
                  ArrayList<AddExp> operands) {
        super(first, operators, operands, "<RelExp>");
    }
}
