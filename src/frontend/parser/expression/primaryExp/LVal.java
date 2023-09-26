package frontend.parser.expression.primaryExp;

import frontend.lexer.Token;
import frontend.parser.expression.Exp;
import frontend.parser.terminal.Ident;

import java.util.ArrayList;

/**
 * LVal 左值表达式类
 * LVal → Ident {'[' Exp ']'} //1.普通变量 2.一维数组 3.二维数组
 * @author SYH
 * @date 2023/09/26
 */
public class LVal implements PrimaryExpEle {
    private final String name = "<LVal>";
    private Ident ident;
    private ArrayList<Token> leftBrackets;
    private ArrayList<Exp> exps;
    private ArrayList<Token> rightBrackets;

    public LVal(Ident ident,
                ArrayList<Token> leftBrackets,
                ArrayList<Exp> exps,
                ArrayList<Token> rightBrackets) {
        this.ident = ident;
        this.leftBrackets = leftBrackets;
        this.exps = exps;
        this.rightBrackets = rightBrackets;
    }
}
