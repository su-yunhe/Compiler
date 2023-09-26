package frontend.parser.statement.stmt;

import frontend.lexer.Token;
import frontend.parser.expression.Exp;
import frontend.parser.terminal.FormatString;

import java.util.ArrayList;

public class StmtPrint implements StmtEle{
    private Token printf; // 'printf'
    private Token leftParent; // '('
    private FormatString formatString;
    private ArrayList<Token> commmas; // ',' MAY exist
    private ArrayList<Exp> exps;
    private Token rightParent; // ')'
    private Token semicn; // ';'

    public StmtPrint(Token printf,
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
