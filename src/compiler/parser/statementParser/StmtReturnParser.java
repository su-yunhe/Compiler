package compiler.parser.statementParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.ExpParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.statement.stmt.StmtReturn;
import utils.ErrorUtils;

public class StmtReturnParser {
    /* StmtPrint Attributes */
    private Token returnTk; // 'return'
    private Exp exp;
    private Token semicn; // ';'
    private StmtReturn stmtReturn = null;
    public StmtReturn parseStmtReturn() {
        returnTk = TLIterator.readNext();
        if (!returnTk.getType().equals(LexType.RETURNTK)) {
            System.out.println("EXPECT RETURNTK IN STMTRETURNPARSER");
        }
        ExpParser expParser = new ExpParser();
        semicn = TLIterator.readNext();
        if (semicn.getType().equals(LexType.SEMICN)) {
            stmtReturn = new StmtReturn(returnTk, semicn);
        } else {
            if (semicn.getType().equals(LexType.RBRACE)) {
                // TODO: 处理i类错误：缺失`;`【void】
                handleIErrorVoid();
                stmtReturn = new StmtReturn(returnTk, semicn);
            } else {
                TLIterator.unRead(1);
                exp = expParser.parseExp();
                semicn = TLIterator.readNext();
                // TODO: 处理i类错误：缺失`;`【int】
                handleIErrorInt();
                stmtReturn = new StmtReturn(returnTk, exp, semicn);
            }
        }
        return stmtReturn;
    }

    /**
     * 处理i类错误：缺失`;`
     */
    private void handleIErrorVoid() {
        TLIterator.unRead(2);
        Token prev = TLIterator.readNext();
        ErrorUtils.handleI(prev);
    }

    private void handleIErrorInt() {
        if (!semicn.getType().equals(LexType.SEMICN)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleI(prev);
        }
    }
}
