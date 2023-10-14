package compiler.parser.statementParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.ExpParser;
import compiler.parser.expressionParser.primaryExpParser.LValParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.primaryExp.LVal;
import struct.syntaxTree.statement.stmt.StmtAssign;
import utils.ErrorUtils;
import eumes.SymbolType;

public class StmtAssignParser {
    /* StmtAssign Attributes */
    private LVal lval = null;
    private Token eq; // '='
    private Exp exp;
    private Token semicn; // ';'
    
    /**
     * StmtAssign = LVal '=' Exp ';'
     * @return {@link StmtAssign}
     */
    public StmtAssign parseStmtAssign() {
        lval = new LValParser().parseLVal();
        // TODO: 处理 h 类错误: 修改常量
        handleHError(lval);
        eq = TLIterator.readNext();
        if (!eq.getType().equals(LexType.ASSIGN)) {
            System.out.println("EXPECT = HERE in parseStmtAssign");
        }
        exp = new ExpParser().parseExp();
        semicn = TLIterator.readNext();
        // TODO: 处理i类错误：缺失`;`
        handleIError();
        return new StmtAssign(lval, eq, exp, semicn);
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
}
