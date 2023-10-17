package compiler.parser.statementParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.statement.stmt.StmtBreak;
import utils.ErrorUtils;
import struct.symbolTable.STStack;

public class StmtBreakParser {
    /* StmtBreak Attributes */
    private Token breakTk; // 'break'
    private Token semicn; // ';'
    public StmtBreak parseStmtBreak() {
        breakTk = TLIterator.readNext();
        // TODO: 处理 m 类错误：在非循环块中使用 break 语句
        handleMError(breakTk);
        if (!this.breakTk.getType().equals(LexType.BREAKTK)) {
            System.out.println("EXPECT BREAKTK IN STMTBREAKPARSER");
        }
        semicn = TLIterator.readNext();
        // TODO: 处理i类错误：缺失`;`
        handleIError();
        return new StmtBreak(breakTk, semicn);
    }

    /**
     * 处理i类错误：缺失`;`
     */
    private void handleIError() {
        if (!semicn.getType().equals(LexType.SEMICN)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleI(prev);
        }
    }

    /**
     * 处理 m 类错误：在非循环块中使用 break 语句
     */
    private void handleMError(Token breakTk) {
        if (STStack.getCurrentTable().getCycleDepth() <= 0) {
            ErrorUtils.handleM(breakTk);
        }
    }
}
