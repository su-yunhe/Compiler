package frontend.parser.struct.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.struct.expression.Exp;

/**
 * StmtExp 表达式语句类
 * StmtExp = [Exp] ';'
 * @author SYH
 * @date 2023/09/26
 */
public class StmtExp implements StmtEle{
    private Exp exp;
    private Token semicn; // ';'

    public StmtExp(Exp exp, Token semicn) {
        this.exp = exp;
        this.semicn = semicn;
    }
}
