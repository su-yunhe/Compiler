package struct.syntaxTree.expression.multiExp;

import struct.token.Token;
import struct.syntaxTree.expression.unaryExp.UnaryExp;

import java.util.ArrayList;

/**
 * MulExp 乘除模表达式
 * MulExp -> UnaryExp { ('*' | '/' | '%') UnaryExp }
 * @author SYH
 * @date 2023/09/26
 */
public class MulExp extends ASTMultiExp<UnaryExp> {
    public MulExp(UnaryExp first, ArrayList<Token> operators, ArrayList<UnaryExp> operands) {
        super(first, operators, operands, "<MulExp>");
    }

    public int getDimension() {
        return this.getFirst().getDimension();
    }
}
