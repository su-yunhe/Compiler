package struct.syntaxTree.expression.multiExp;

import struct.token.Token;

import java.util.ArrayList;

/**
 * RelExp 关系表达式
 * RelExp -> AddExp { ('<' | '>' | '<=' | '>=') AddExp }
 * @author SYH
 * @date 2023/09/26
 */
public class RelExp extends ASTMultiExp<AddExp> {
    public RelExp(AddExp first, ArrayList<Token> operators,
                  ArrayList<AddExp> operands) {
        super(first, operators, operands, "<RelExp>");
    }
}
