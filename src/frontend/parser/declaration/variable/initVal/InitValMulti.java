package frontend.parser.declaration.variable.initVal;

import frontend.lexer.Token;

import java.util.ArrayList;

/**
 * 数组变量初值类
 * InitValMulti = '{' [ InitVal { ',' InitVal } ] '}'// 1.表达式初值 2.一维数组初值 3.二维数组初值
 * @author SYH
 * @date 2023/09/26
 */
public class InitValMulti {
    private Token leftBrace; // {
    private InitVal first; // may not exist
    private ArrayList<Token> commas;
    private ArrayList<InitVal> initVals;
    private Token rightBrace; // }

    public InitValMulti(Token leftBrace,
                    InitVal first,
                    ArrayList<Token> commas,
                    ArrayList<InitVal> initVals,
                    Token rightBrace) {
        this.leftBrace = leftBrace;
        this.first = first;
        this.commas = commas;
        this.initVals = initVals;
        this.rightBrace = rightBrace;
    }

}
