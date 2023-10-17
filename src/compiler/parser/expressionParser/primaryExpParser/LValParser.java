package compiler.parser.expressionParser.primaryExpParser;

import enums.LexType;
import struct.symbol.symbol.Symbol;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.ExpParser;
import compiler.parser.terminalParser.IdentParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.primaryExp.LVal;
import struct.syntaxTree.terminal.Ident;
import utils.ErrorUtils;
import struct.symbolTable.STStack;

import java.util.ArrayList;

public class LValParser {
    /* LVal Attributes */
    private Ident ident = null;
    private ArrayList<Token> leftBrackets = new ArrayList<>(); // '['
    private ArrayList<Exp> exps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();

    /**
     * LVal → Ident {'[' Exp ']'}
     * @return {@link LVal}
     */
    public LVal parseLVal() {
        leftBrackets = new ArrayList<>();
        exps = new ArrayList<>();
        rightBrackets = new ArrayList<>();

        ident = new IdentParser().parseIdent();
        // TODO: 处理 c 类错误：使用了未定义的标识符
        handleCError(ident);
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.LBRACK)) { // '['
            leftBrackets.add(token);
            exps.add(new ExpParser().parseExp());
            token = TLIterator.readNext(); // ']'
            // TODO: 处理 k 类错误：缺失`]`
            handleKError(token);
            rightBrackets.add(token);
            token = TLIterator.readNext();
        }
        TLIterator.unRead(1);
        return new LVal(ident, leftBrackets, exps, rightBrackets);
    }

    /**
     * 检查并处理 c 类错误：使用了未定义的标识符
     * @param ident ident
     */
    private void handleCError(Ident ident) {
        Symbol symbol = STStack.getDefinedSymbol(ident.getName());
        if (symbol == null) {
            ErrorUtils.handleC(ident);
        } else {
            ident.setSymbol(symbol);
        }
    }

    /**
     * 检查并处理 k 类错误：缺失`]`
     * @param token token
     */
    private void handleKError(Token token) {
        if (!token.getType().equals(LexType.RBRACK)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleK(prev);
        }
    }
}
