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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.printf.toString());
        sb.append(this.leftParent.toString());
        sb.append(this.formatString.toString());
        if (this.commmas != null && this.exps != null &&
                this.commmas.size() == this.exps.size()) {
            int len = this.commmas.size();
            for (int i = 0; i < len; i++) {
                sb.append(this.commmas.get(i).toString());
                sb.append(this.exps.get(i).toString());
            }
        }
        sb.append(this.rightParent.toString());
        sb.append(this.semicn.toString());
        return sb.toString();
    }
}
