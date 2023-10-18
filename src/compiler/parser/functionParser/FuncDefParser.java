package compiler.parser.functionParser;

import enums.LexType;
import compiler.parser.statementParser.BlockParser;
import enums.ReturnType;
import struct.symbol.symbol.Symbol;
import struct.symbol.symbol.SymbolFunc;
import struct.syntaxTree.statement.BlockItem;
import struct.syntaxTree.statement.stmt.Stmt;
import struct.syntaxTree.statement.stmt.StmtReturn;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.terminalParser.IdentParser;
import struct.syntaxTree.function.FuncDef;
import struct.syntaxTree.function.FuncFParams;
import struct.syntaxTree.function.funcType.FuncType;
import struct.syntaxTree.statement.Block;
import struct.syntaxTree.terminal.Ident;
import utils.ErrorUtils;
import struct.symbolTable.STStack;
import enums.SymbolType;

import java.util.ArrayList;

public class FuncDefParser {
    /* FuncDef Attributes */
    private FuncType funcType = null;
    private Ident ident = null;
    private Token leftParent = null; // '('
    private FuncFParams funcFParams = null; // MAY exist
    private Token rightParent = null; // ')'
    private Block block = null;
    private FuncDef funcDef = null;
    private SymbolFunc symbolFunc = null;
    private boolean isDefCorrect = true;
    
    /**
     * FuncDef → FuncType Ident '(' [FuncFParams] ')' Block
     * @return {@link FuncDef}
     */
    public FuncDef parseFuncDef() {
        /* FuncType */
        funcType = new FuncTypeParser().parseFuncType();
        /* Ident */
        ident = new IdentParser().parseIdent();
        // TODO: 添加函数符号 & 处理 b 类错误：名字重定义
        addSymbol(funcType, ident);
        // TODO: 压入新的符号表
        STStack.pushST();
        /* ( */
        leftParent = TLIterator.readNext();
        rightParent = TLIterator.readNext();
        if (rightParent.getType().equals(LexType.RPARENT)) {
            // TODO: 将函数定义中的形参加入 SymbolFunc 的形参列表中
            setPmtList(funcFParams, symbolFunc);
            /* Block */
            block = new BlockParser().parseBlock();
            funcDef = new FuncDef(funcType, ident, leftParent, rightParent, block);
        } else {
            if (rightParent.getType().equals(LexType.LBRACE)) {
                // TODO: 处理 j 类错误：缺失右小括号`)`【无参数时】
                handleJErrorNull();
                // TODO: 将函数定义中的形参加入 SymbolFunc 的形参列表中
                setPmtList(funcFParams, symbolFunc);
                block = new BlockParser().parseBlock();
                funcDef = new FuncDef(funcType, ident, leftParent, rightParent, block);
            } else {
                /* FuncFParams */
                TLIterator.unRead(1);
                funcFParams = new FuncFParamsParser().parseFuncFParams();
                /* ) */
                rightParent = TLIterator.readNext();
                // TODO: 处理 j 类错误：缺失右小括号`)`【有参数时】
                handleJErrorFuncFParams();
                /* Block */
                // TODO: 将函数定义中的形参加入 SymbolFunc 的形参列表中
                setPmtList(funcFParams, symbolFunc);
                block = new BlockParser().parseBlock();
                funcDef = new FuncDef(funcType, ident, leftParent, funcFParams, rightParent, block);
            }
        }
        // TODO: 处理 f 类错误：无返回值的函数存在不匹配的return语句
        handleFError(block);
        // TODO: 处理 g 类错误：缺失有返回值的 return 语句
        handleGError(block);
        System.out.println(STStack.STStackToString());
        // TODO: 弹出当前符号表
        STStack.popST();
        return funcDef;
    }

    /**
     * @param funcType 函数类型
     * @param ident ident
     */
    private void addSymbol(FuncType funcType, Ident ident) {
        int dimension = -2;
        if (funcType.getType().equals(LexType.INTTK)) {
            dimension = 0;
        } else if (funcType.getType().equals(LexType.VOIDTK)) {
            dimension = -1;
        } else {
            System.out.println("Error in parseFuncDef");
        }
        symbolFunc = new SymbolFunc(ident.getLineNum(), ident.getName(), SymbolType.FUNC, dimension);
        ident.setSymbol(symbolFunc);
        if (STStack.isSymbolRedefined(symbolFunc)) {
            // TODO: 处理b类错误：名字重定义
            handleBError(ident);
        } else {
            STStack.getCurrentTable().addSymbol(symbolFunc);
        }
    }

    private void setPmtList(FuncFParams funcFParams, SymbolFunc symbolFunc) {
        ArrayList<Symbol> symbols;
        if (!isDefCorrect) {
            symbols = null;
        } else if (funcFParams == null) {
            symbols = new ArrayList<>();
        } else {
            symbols = funcFParams.getSymbols();
        }
        symbolFunc.setValue(symbols);
    }

    /**
     * 处理 b 类错误: 名字重定义
     */
    private void handleBError(Ident ident) {
        ErrorUtils.handleB(ident);
    }

    /**
     * 处理 f 类错误：无返回值的函数存在不匹配的return语句
     */
    private void handleFError(Block block) {
        if (funcType.getType().equals(LexType.VOIDTK)
                && block.getReturnType().equals(ReturnType.INT)) {
            BlockItem blockItem = block.getLastBlockItem();
            Stmt stmt = (Stmt) blockItem.getBlockItemEle();
            StmtReturn stmtReturn = (StmtReturn) stmt.getStmtEle();
            ErrorUtils.handleF(stmtReturn);
        }
    }

    /**
     *  处理 g 类错误：缺失有返回值的 return 语句
     */
    private void handleGError(Block block) {
        if (funcType.getType().equals(LexType.INTTK) &&
                !block.getReturnType().equals(ReturnType.INT)) {
            ErrorUtils.handleG(block);
        }
    }

    /**
     * 处理 j 类错误：缺失右小括号`)`【无参数时】
     */
    private void handleJErrorNull() {
        TLIterator.unRead(2);
        Token prev = TLIterator.readNext();
        ErrorUtils.handleJ(prev);
        isDefCorrect = false;
    }

    /**
     * 检查并处理 j 类错误：缺失右小括号`)`【有参数时】
     */
    private void handleJErrorFuncFParams() {
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            TLIterator.unRead(2);
            Token prev = TLIterator.readNext();
            ErrorUtils.handleJ(prev);
            isDefCorrect = false;
        }
    }
}
