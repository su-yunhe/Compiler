package compiler.parser.statementParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.statement.stmt.StmtContinue;
import utils.ErrorUtils;
import struct.symbolTable.STStack;

public class StmtContinueParser {
    /* StmtContinue Attributes */
    private Token continueTk = null; // 'continue'
    // TODO: 处理 m 类错误：在非循环块中使用 break 语句
    private Token semicn = null; // ';'
    public StmtContinue parseStmtContinue() {
        continueTk = TLIterator.readNext();
        handleMError(continueTk);
        if (!continueTk.getType().equals(LexType.CONTINUETK)) {
            System.out.println("EXPECT CONTINUETK IN STMTCONTINUEPARSER");
        }
        semicn = TLIterator.readNext();
        // TODO: 处理i类错误：缺失`;`
        handleIError();
        return new StmtContinue(continueTk, semicn);
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
    private void handleMError(Token continueTk) {
        if (STStack.getCurrentTable().getCycleDepth() <= 0) {
            ErrorUtils.handleM(continueTk);
        }
    }
}
