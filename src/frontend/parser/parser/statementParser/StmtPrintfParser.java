package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.expressionParser.ExpParser;
import frontend.parser.struct.expression.Exp;
import frontend.parser.struct.statement.stmt.StmtPrintf;
import frontend.parser.struct.terminal.FormatString;

import java.util.ArrayList;

public class StmtPrintfParser {
    /* StmtPrint Attributes */
    private Token printf; // 'printf'
    private Token leftParent; // '('
    private FormatString formatString;
    private ArrayList<Token> commmas = new ArrayList<>(); // ','
    private ArrayList<Exp> exps = new ArrayList<>();
    private Token rightParent; // ')'
    private Token semicn; // ';'
    public StmtPrintf parseStmtPrint() {
        this.commmas = new ArrayList<>();
        this.exps = new ArrayList<>();
        this.printf = TLIterator.readNextToken();
        if (!this.printf.getType().equals(LexType.PRINTFTK)) {
            System.out.println("EXPEXT PRINTF IN STMTPRINTFPARSER");
        }
        this.leftParent = TLIterator.readNextToken();
        if (!this.leftParent.getType().equals(LexType.LPARENT)) {
            System.out.println("EXPECT LPARENT IN STMTPRINTFPARSER");
        }
        this.formatString = new FormatString(TLIterator.readNextToken());
        Token token = TLIterator.readNextToken();
        while (token.getType().equals(LexType.COMMA)) {
            this.commmas.add(token);
            ExpParser expParser = new ExpParser();
            this.exps.add(expParser.parseExp());
            token = TLIterator.readNextToken();
        }
        this.rightParent = token;
        this.semicn = TLIterator.readNextToken();
        return new StmtPrintf(this.printf, this.leftParent,
                this.formatString, this.commmas, this.exps, this.rightParent, this.semicn);
    }
}
