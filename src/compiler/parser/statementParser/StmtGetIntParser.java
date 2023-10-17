package compiler.parser.statementParser;

import enums.LexType;
import compiler.parser.expressionParser.primaryExpParser.LValParser;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.expression.primaryExp.LVal;
import struct.syntaxTree.statement.stmt.StmtGetInt;
import utils.ErrorUtils;
import enums.SymbolType;

public class StmtGetIntParser {
    /* StmtGetint Attributes */
    private LVal lval = null;
    private Token eq = null; // '='
    private Token getint = null; // 'getint'
    private Token leftParent = null; // '('
    private Token rightParent = null; // ')'
    private Token semicn = null; // ';'
    /**
     * LVal '=' 'getint''('')'';'
     * @return {@link StmtGetInt}
     */
    public StmtGetInt parseStmtGetInt() {
        lval = new LValParser().parseLVal();
        // TODO: 处理 h 类错误: 修改常量
        handleHError(lval);
        eq = TLIterator.readNext();
        getint = TLIterator.readNext();
        leftParent = TLIterator.readNext();
        rightParent = TLIterator.readNext();
        // TODO: 处理 j 类错误：缺失`)`
        handleJError();
        semicn = TLIterator.readNext();
        // TODO: 处理i类错误：缺失`;`
        handleIError();
        return new StmtGetInt(lval, eq, getint, leftParent, rightParent, semicn);
    }

    /**
     * 处理 h 类错误: 修改常量
     * @param lVal 左值表达式
     */
    private void handleHError(LVal lVal) {
        if (lVal.getSymbolType() == null) {
            return;
        }
        if (lVal.getSymbolType().equals(SymbolType.CON) ||
                lVal.getSymbolType().equals(SymbolType.CON1) ||
                lVal.getSymbolType().equals(SymbolType.CON2)) {
            ErrorUtils.handleH(lVal);
        }
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
     * 检查并处理 j 类错误：缺失右小括号`)`
     */
    private void handleJError() {
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleJ(prev);
        }
    }
}
