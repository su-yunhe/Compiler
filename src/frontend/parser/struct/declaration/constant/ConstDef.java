package frontend.parser.struct.declaration.constant;

import frontend.lexer.Token;
import frontend.parser.struct.declaration.constant.constInitVal.ConstInitVal;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

/**
 * ConstDef 常数定义类
 * ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal // 包含普通变量、一维数组、二维数组共三种情况
 * @author SYH
 * @date 2023/09/26
 */
public class ConstDef{
    private final String name = "<ConstDef>";
    private Ident ident;
    private ArrayList<Token> leftBracks; // '['
    private ArrayList<ConstExp> constExps;
    private ArrayList<Token> rightBrackets; // ']'
    private Token eq; // '='
    private ConstInitVal constInitval;

    public ConstDef(Ident ident,
                    ArrayList<Token> leftBracks,
                    ArrayList<ConstExp> constExps,
                    ArrayList<Token> rightBrackets,
                    Token eq,
                    ConstInitVal constInitval) {
        this.ident = ident;
        this.leftBracks = leftBracks;
        this.constExps = constExps;
        this.rightBrackets = rightBrackets;
        this.eq = eq;
        this.constInitval = constInitval;
    }
}
