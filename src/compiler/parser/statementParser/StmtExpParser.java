package compiler.parser.statementParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.ExpParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.statement.stmt.StmtExp;
import utils.ErrorUtils;

public class StmtExpParser {
    /* StmtExp Attributes */
    private Exp exp = null;
    private Token semicn = null; // ';'
    public StmtExp parseStmtExp() {
        ExpParser expParser = new ExpParser();
        exp = expParser.parseExp();
        semicn = TLIterator.readNext();
        // TODO: 处理i类错误：缺失`;`
        handleIError();
        return new StmtExp(exp, semicn);
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
