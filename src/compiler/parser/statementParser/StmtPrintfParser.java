package compiler.parser.statementParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.ExpParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.statement.stmt.StmtPrintf;
import struct.syntaxTree.terminal.FormatString;
import utils.ErrorUtils;

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
        commmas = new ArrayList<>();
        exps = new ArrayList<>();
        printf = TLIterator.readNext();
        if (!printf.getType().equals(LexType.PRINTFTK)) {
            System.out.println("EXPEXT PRINTF IN STMTPRINTFPARSER");
        }
        leftParent = TLIterator.readNext();
        if (!leftParent.getType().equals(LexType.LPARENT)) {
            System.out.println("EXPECT LPARENT IN STMTPRINTFPARSER");
        }
        formatString = new FormatString(TLIterator.readNext());
        // TODO: 处理 a 类错误
        ErrorUtils.handleA(formatString);
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.COMMA)) {
            commmas.add(token);
            ExpParser expParser = new ExpParser();
            exps.add(expParser.parseExp());
            token = TLIterator.readNext();
        }
        rightParent = token;
        // TODO: 处理 j 类错误：缺失右小括号`)`
        handleJError();
        semicn = TLIterator.readNext();
        // TODO: 处理 i 类错误：缺失分号`;`
        handleIError();
        // TODO: 处理 l 类异常：格式字符与表达式个数不匹配
         handleLError(formatString);
        return new StmtPrintf(printf, leftParent, formatString, commmas, exps, rightParent, semicn);
    }

    /**
     * 处理 i 类错误：缺失`;`
     */
    private void handleIError() {
        if (!semicn.getType().equals(LexType.SEMICN)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleI(prev);
        }
    }

    /**
     * 检查并处理 j 类错误：缺失右小括号`)`
     */
    private void handleJError() {
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleJ(prev);
        }
    }

    /**
     * 处理 l 类异常：格式字符与表达式个数不匹配
     * @param formatString 格式字符串
     */
    private void handleLError(FormatString formatString) {
        String target = "%d";
        String content = formatString.getContent();
        /* 格式字符串个数 */
        int cnt1 = (content.length() - content.replace(target, "").length()) / target.length();
        /* 表达式个数 */
        int cnt2 = exps.size();
        if (cnt1 != cnt2) {
            ErrorUtils.handleL(formatString);
        }
    }
}
