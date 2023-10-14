package compiler.parser.functionParser;

import eumes.ReturnType;
import eumes.SymbolType;
import eumes.LexType;
import compiler.parser.statementParser.BlockParser;
import struct.symbolTable.STStack;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.function.MainFuncDef;
import struct.syntaxTree.statement.Block;
import struct.syntaxTree.terminal.Ident;
import utils.ErrorUtils;
import struct.symbol.SymbolFunc;

import java.util.ArrayList;

public class MainFuncDefParser {
    /* MainFuncDef Attributes */
    private Token intTk; // 'int'
    private Token mainTk; // 'main'
    private Token leftParent; // '('
    private Token rightParent; // ')'
    private Block block;
    public MainFuncDef parseMainFuncDef() {
        intTk = TLIterator.readNext();
        mainTk = TLIterator.readNext();
        // TODO: 添加新符号 & 处理 b 类错误：名字重定义
        addSymbol(mainTk);
        // TODO: 创建新符号表
        STStack.pushST();
        leftParent = TLIterator.readNext();
        rightParent = TLIterator.readNext();
        // TODO: 处理j类错误：缺失右小括号`)`
        handleJError();
        block = new BlockParser().parseBlock();
        // TODO: 处理 g 类错误：缺失有返回值的 return 语句
        handleGError(block);
        STStack.popST();
        return new MainFuncDef(intTk, mainTk, leftParent, rightParent, block);
    }

    private void addSymbol(Token mainTk) {
        SymbolFunc symbolFunc = new SymbolFunc(mainTk.getLineNum(), mainTk.getContent(),
                SymbolType.FUNC, 0);
        if (STStack.isSymbolRedefined(symbolFunc)) {
            // TODO: 处理 b 类错误：名字重定义
            handleBError(new Ident(mainTk));
        } else {
            STStack.getCurrentTable().addSymbol(symbolFunc);
            symbolFunc.setValue(new ArrayList<>());
        }
    }

    /**
     * 处理 b 类错误：名字重定义
     */
    private void handleBError(Ident ident) {
        ErrorUtils.handleB(ident);
    }

    /**
     *  处理 g 类错误：缺失有返回值的 return 语句
     */
    private void handleGError(Block block) {
        if (!block.getReturnType().equals(ReturnType.INT)) {
            ErrorUtils.handleG(block);
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
