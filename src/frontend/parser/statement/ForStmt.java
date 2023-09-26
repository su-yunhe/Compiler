package frontend.parser.statement;

import frontend.lexer.Token;
import frontend.parser.expression.Exp;
import frontend.parser.expression.primaryExp.LVal;

/**
 * 语句 ForStmt → LVal '=' Exp // 存在即可
 * @author SYH
 * @date 2023/09/26
 */
public class ForStmt {
    private LVal lVal;
    private Token eq; // =
    private Exp exp;

    public ForStmt(LVal lVal, Token eq, Exp exp) {
        this.lVal = lVal;
        this.eq = eq;
        this.exp = exp;
    }

}
