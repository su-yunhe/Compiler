package frontend.parser.struct.expression;

import frontend.lexer.Token;

import java.util.ArrayList;

/**
 * FuncRParams 函数实参表类
 * FuncRParams → Exp { ',' Exp } // 1.花括号内重复0次 2.花括号内重复多次 3.Exp需要覆盖数组传参和部分数组传参
 * @author SYH
 * @since 2023/09/26
 */
public class FuncRParams {
    private final String name = "<FuncRParams>";
    private Exp first;
    /* commas 和 exps大小应当相同，且commas应当只有逗号一种Token */
    /* 这里可能需要进行某种验证 */
    private ArrayList<Token> commas;
    private ArrayList<Exp> exps;

    public FuncRParams(Exp first, ArrayList<Token> commas, ArrayList<Exp> exps) {
        this.first = first;
        this.commas = commas;
        this.exps = exps;
    }
}
