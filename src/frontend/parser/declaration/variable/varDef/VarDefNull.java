package frontend.parser.declaration.variable.varDef;

import frontend.lexer.Token;
import frontend.parser.expression.ConstExp;
import frontend.parser.terminal.Ident;

import java.util.ArrayList;

/**
 * VarDefNull 无初始化的变量定义类
 *  VarDefNull = Ident { '[' ConstExp ']' }
 * @author SYH
 * @date 2023/09/26
 */
public class VarDefNull implements VarDefEle {
    private Ident ident;
    private ArrayList<Token> leftBraces;
    private ArrayList<ConstExp> constExps;
    private ArrayList<Token> rightBraces;

    public VarDefNull(Ident ident,
                      ArrayList<Token> leftBraces,
                      ArrayList<ConstExp> constExps,
                      ArrayList<Token> rightBraces) {
        this.ident = ident;
        this.leftBraces = leftBraces;
        this.constExps = constExps;
        this.rightBraces = rightBraces;
    }
}
