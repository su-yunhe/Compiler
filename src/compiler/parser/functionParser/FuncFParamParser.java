package compiler.parser.functionParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.declarationParser.constantParser.BTypeParser;
import compiler.parser.expressionParser.ConstExpParser;
import compiler.parser.terminalParser.IdentParser;
import struct.syntaxTree.declaration.BType;
import struct.syntaxTree.expression.ConstExp;
import struct.syntaxTree.function.FuncFParam;
import struct.syntaxTree.terminal.Ident;
import utils.ErrorUtils;
import struct.symbolTable.STStack;
import struct.symbol.*;
import enums.SymbolType;

import java.util.ArrayList;

public class FuncFParamParser {
    /* FuncFParam Attributes */
    private BType btype = null;
    private Ident ident = null;
    private Token leftBracketFirst = null;
    private Token rightBracketFirst = null;
    private ArrayList<Token> leftBrackets = new ArrayList<>();
    private ArrayList<ConstExp> constExps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();
    private FuncFParam funcFParam = null;
    private boolean isDefCorrect = true;
    /**
     * FuncFParam → BType Ident ['[' ']' { '[' ConstExp ']' }] // 1.普通变量2.一维 数组变量 3.二维数组变量
     * @return {@link FuncFParam}
     */
    public FuncFParam parseFuncFParam() {
        leftBrackets = new ArrayList<>();
        constExps = new ArrayList<>();
        rightBrackets = new ArrayList<>();
        /* BType */
        btype = new BTypeParser().parseBtype();
        /* Ident */
        ident = new IdentParser().parseIdent();

        leftBracketFirst = TLIterator.readNext();
        if (leftBracketFirst.getType().equals(LexType.LBRACK)) { // [
            /* 一维数组变量 和 二维数组变量 ['[' ']' { '[' ConstExp ']' }] */
            rightBracketFirst = TLIterator.readNext();
            // TODO: 处理 k 类错误：缺失右中括号`]`
            handleKError(rightBracketFirst);
            Token token = TLIterator.readNext();
            while (token.getType().equals(LexType.LBRACK)) {
                leftBrackets.add(token);
                constExps.add(new ConstExpParser().parseConstExp());
                rightBrackets.add(TLIterator.readNext());
                // TODO: 处理 k 类错误：缺失右中括号`]`
                handleKError(rightBrackets.get(rightBrackets.size() - 1));
                token = TLIterator.readNext();
            }
            TLIterator.unRead(1);
            funcFParam = new FuncFParam(btype, ident, leftBracketFirst, rightBracketFirst, leftBrackets, constExps, rightBrackets);
        } else {
            /* 普通变量 */
            TLIterator.unRead(1);
            funcFParam = new FuncFParam(btype, ident);
        }
        // TODO: 添加新符号 & 处理 b 类错误：名字重定义
        addSymbol(ident);
        return funcFParam;
    }

    private void addSymbol(Ident ident) {
        Symbol symbol;
        /* 判断维数 */
        if (leftBracketFirst.getType().equals(LexType.LBRACK) && leftBrackets.size() == 1) {
            symbol = new SymbolVar2(ident.getLineNum(), ident.getName(), SymbolType.VAR2, 2);
        } else if (leftBracketFirst.getType().equals(LexType.LBRACK)) {
            symbol = new SymbolVar1(ident.getLineNum(), ident.getName(), SymbolType.VAR1, 1);
        } else {
            symbol = new SymbolVar(ident.getLineNum(), ident.getName(), SymbolType.VAR, 0);
        }

        ident.setSymbol(symbol);
        if (STStack.isSymbolRedefined(symbol)) {
            // TODO: 处理 b 类错误：名字重定义
            handleBError(ident);
        } else {
            STStack.getCurrentTable().addSymbol(symbol);
            if (isDefCorrect) {

            }
        }
    }

    public Symbol getSymbol() {
        return ident.getSymbol();
    }

    /**
     * 检查并处理 b 类错误：名字重定义
     */
    private void handleBError(Ident ident) {
        ErrorUtils.handleB(ident);
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
            isDefCorrect = false;
        }
    }
}
