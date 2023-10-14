package struct.syntaxTree.statement.stmt;

import struct.token.Token;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.terminal.FormatString;

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
        sb.append(printf.toString());
        sb.append(leftParent.toString());
        sb.append(formatString.toString());
        if (commmas != null && exps != null &&
                commmas.size() == exps.size()) {
            int len = commmas.size();
            for (int i = 0; i < len; i++) {
                sb.append(commmas.get(i).toString());
                sb.append(exps.get(i).toString());
            }
        }
        sb.append(this.rightParent.toString());
        sb.append(this.semicn.toString());
        return sb.toString();
    }
}
