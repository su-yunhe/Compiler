package compiler.parser.expressionParser.unaryExpParser;

import enums.LexType;
import compiler.parser.expressionParser.FuncRParamsParser;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.terminalParser.IdentParser;
import struct.syntaxTree.expression.Exp;
import struct.syntaxTree.expression.FuncRParams;
import struct.syntaxTree.expression.unaryExp.UnaryExpFunc;
import struct.syntaxTree.terminal.Ident;
import utils.ErrorUtils;
import struct.symbolTable.STStack;
import struct.symbol.Symbol;
import struct.symbol.SymbolFunc;

import java.util.ArrayList;

public class UnaryExpFuncParser {
    /* UnaryExpFunc Attributes */
    private Ident ident = null;
    private Token leftParent; // '('
    private FuncRParams funcRParams = null;
    private Token rightParent; // ')'
    private UnaryExpFunc unaryExpFunc = null;
    private int dimension;
    /**
     * UnaryExpFunc = Ident '(' [FuncRParams] ')'
     * @return {@link UnaryExpFunc}
     */
    public UnaryExpFunc parseUnaryFuncExp() {
        /* Ident */
        ident = new IdentParser().parseIdent();
        System.out.println(ident.getName());
        // TODO: 处理c类错误：未定义名字 & 获取维数
        handleCError(ident);
        /* '(' */
        leftParent = TLIterator.readNext();
        rightParent = TLIterator.readNext();
        if (rightParent.getType().equals(LexType.RPARENT)) {
            unaryExpFunc = new UnaryExpFunc(ident, leftParent, rightParent);
        } else {
            if (rightParent.getType().equals(LexType.SEMICN)) {
                // TODO: 处理 j 类错误：缺失右小括号`)`【无参数时】
                handleJErrorNull();
                unaryExpFunc = new UnaryExpFunc(ident, leftParent, rightParent);
            } else {
                TLIterator.unRead(1);
                /* FuncRParams */
                funcRParams = new FuncRParamsParser().parseFuncRParams();
                rightParent = TLIterator.readNext();
                // TODO: 处理 j 类错误：缺失右小括号`)`【有参数时】
                handleJErrorFuncRParams();
                unaryExpFunc = new UnaryExpFunc(ident, funcRParams, leftParent, rightParent);
            }
        }
        // TODO: 处理 d 类错误：函数参数个数不匹配
        handleDError(ident, funcRParams);
        // TODO: 处理 e 类错误：函数参数类型不匹配
        handleEError(ident, funcRParams);
        return unaryExpFunc;
    }

    /**
     * 检查并处理 c 类错误：未定义名字
     * 并获取维数
     */
    private void handleCError(Ident ident) {
        Symbol symbol = STStack.getDefinedSymbol(ident.getName());
        System.out.println("symbol");
        System.out.println(symbol);
        if (symbol == null) {
            ErrorUtils.handleC(ident);
        } else {
            ident.setSymbol(symbol);
        }
    }

    /**
     * 检查并处理 d 类错误：函数参数个数不匹配
     * 注意：当SymbolFunc未被成功定义时不做这项检查
     */
    private void handleDError(Ident ident, FuncRParams funcRParams) {
        Symbol symbol = STStack.getDefinedSymbol(ident.getName());
        /* 没有该名字的函数，说明已经被c类错误处理过 */
        if (!(symbol instanceof SymbolFunc symbolFunc)) {
            return;
        }
        ArrayList<Symbol> symbols = symbolFunc.getValue();
        /* 函数的形参列表没有成功建立，此时无需检查该错误 */
        if (symbols == null) {
            return;
        }
        /* 无参数说明为正确情况，不应当处理 */
        if (funcRParams == null && symbols.isEmpty()) {
            return;
        }
        /* 参数列表长度不匹配 */
        if (funcRParams == null || funcRParams.getExps().size() != symbols.size()) {
            ErrorUtils.handleD(ident);
        }
    }

    /**
     * 处理 e 类错误：函数参数类型不匹配 <br/>
     * 注意：当SymbolFunc未被成功定义时不做这项检查
     */
    private void handleEError(Ident ident, FuncRParams funcRParams) {
        Symbol symbol = STStack.getDefinedSymbol(ident.getName());
        /* 没有该名字的函数，说明已经被c类错误处理过 */
        if (!(symbol instanceof SymbolFunc symbolFunc)) {
            return;
        }
        ArrayList<Symbol> symbols = symbolFunc.getValue();
        /* 函数的形参列表没有成功建立，此时无需检查该错误 */
        if (symbols == null) {
            return;
        }
        /* 无参数说明为正确情况，不应当处理 */
        if (funcRParams == null && symbols.isEmpty()) {
            return;
        }
        /* 参数列表长度不匹配，说明已经被作为d类错误处理过 */
        if (funcRParams == null || funcRParams.getExps().size() != symbols.size()) {
            return;
        }
        ArrayList<Exp> exps = funcRParams.getExps();
        int len = symbols.size();
        for (int i = 0; i < len; i++) {
            Exp exp = exps.get(i);
            System.out.println(exp.getDimension());
            Symbol symbol1 = symbols.get(i);
            System.out.println(symbol1.getDimension());
            if (exp.getDimension() != symbol1.getDimension()) {
                ErrorUtils.handleE(ident);
                return;
            }
        }
    }

    /**
     * 检查并处理 j 类错误：缺失右小括号`)`【无参数】
     */
    private void handleJErrorNull() {
        TLIterator.unRead(2);
        Token prev = TLIterator.readNext();
        ErrorUtils.handleJ(prev);
    }

    /**
     * 检查并处理 j 类错误：缺失右小括号`)`【无参数】
     */
    private void handleJErrorFuncRParams() {
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleJ(prev);
        }
    }
}
