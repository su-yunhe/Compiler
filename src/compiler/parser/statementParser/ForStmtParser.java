package compiler.parser.statementParser;

import enums.SymbolType;
import struct.token.Token;
import utils.ErrorUtils;
import utils.TLIterator;
import compiler.parser.expressionParser.ExpParser;
import compiler.parser.expressionParser.primaryExpParser.LValParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.primaryExp.LVal;
import struct.syntaxTree.statement.ForStmt;

public class ForStmtParser {
    private LVal lVal = null;
    private Token eq = null; // =
    private Exp exp = null;
    /**
     * ForStmt → LVal '=' Exp
     * @return {@link ForStmt}
     */
    public ForStmt parseForStmt() {
        lVal = new LValParser().parseLVal();
        // TODO: 处理 h 类错误: 修改常量
        handleHError(lVal);
        eq = TLIterator.readNext();
        exp = new ExpParser().parseExp();
        return new ForStmt(lVal, eq, exp);
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
}
