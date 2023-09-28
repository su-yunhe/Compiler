package frontend.parser.struct.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.terminal.FormatString;

import java.util.ArrayList;

/**
 * StmtPrintf printf语句
 * StmtPrintf = 'printf''('FormatString{','Exp}')'';' // 1.有 Exp 2.无 Exp
 * @author SYH
 * @date 2023/09/26
 */
public class StmtPrintf implements StmtEle{
    private Token printf; // 'printf'
    private Token leftParent; // '('
    private FormatString formatString;
    private ArrayList<Token> commmas; // ',' MAY exist
    private ArrayList<Exp> exps;
    private Token rightParent; // ')'
    private Token semicn; // ';'

    public StmtPrintf(Token printf,
                      Token leftParent,
                      FormatString formatString,
                      ArrayList<Token> commas,
                      ArrayList<Exp> exps,
                      Token rightParent,
                      Token semicn) {
        this.printf = printf;
        this.leftParent = leftParent;
        this.formatString = formatString;
        this.commmas = commas;
        this.exps = exps;
        this.rightParent = rightParent;
        this.semicn = semicn;
    }
}
