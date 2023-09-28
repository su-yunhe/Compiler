package frontend.parser.struct.declaration.variable.varDef;

import frontend.lexer.Token;
import frontend.parser.struct.declaration.variable.initVal.InitVal;
import frontend.parser.struct.expression.ConstExp;
import frontend.parser.struct.terminal.Ident;

import java.util.ArrayList;

/**
 * VarDefInit 初始化的变量定义类
 * VarDefInit = Ident { '[' ConstExp ']' } '=' InitVal
 * @author SYH
 * @date 2023/09/26
 */
public class VarDefInit implements VarDefEle {
    private Ident ident;
    private ArrayList<Token> leftBraces; // '['
    private ArrayList<ConstExp> constExps;
    private ArrayList<Token> rightBraces; // ']'
    private Token eq;
    private InitVal initVal;

    public VarDefInit(Ident ident,
                      ArrayList<Token> leftBraces,
                      ArrayList<ConstExp> constExps,
                      ArrayList<Token> rightBraces,
                      Token eq,
                      InitVal initVal) {
        this.ident = ident;
        this.leftBraces = leftBraces;
        this.constExps = constExps;
        this.rightBraces = rightBraces;
        this.eq = eq;
        this.initVal = initVal;
    }
}