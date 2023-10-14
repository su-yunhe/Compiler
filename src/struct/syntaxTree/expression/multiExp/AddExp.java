package struct.syntaxTree.expression.multiExp;

import struct.token.Token;

import java.util.ArrayList;

/**
 * AddExp 加减表达式类
 * AddExp -> MulExp { ('+' | '-') MulExp }
 * @author SYH
 * @date 2023/09/26
 */
public class AddExp extends ASTMultiExp<MulExp> {
    public AddExp(MulExp first, ArrayList<Token> tokens, ArrayList<MulExp> operands) {
        super(first, tokens, operands, "<AddExp>");
    }

    public int getDimension() {
        return this.getFirst().getDimension();
    }


}
