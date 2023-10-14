package compiler.parser.declarationParser.variableParser;

import eumes.LexType;
import compiler.parser.expressionParser.ConstExpParser;
import compiler.parser.terminalParser.IdentParser;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.declaration.variable.initVal.InitVal;
import struct.syntaxTree.declaration.variable.varDef.VarDef;
import struct.syntaxTree.declaration.variable.varDef.VarDefEle;
import struct.syntaxTree.declaration.variable.varDef.VarDefInit;
import struct.syntaxTree.declaration.variable.varDef.VarDefNull;
import struct.syntaxTree.expression.ConstExp;
import struct.syntaxTree.terminal.Ident;
import utils.ErrorUtils;
import struct.symbolTable.STStack;
import struct.symbol.*;
import eumes.SymbolType;

import java.util.ArrayList;

public class VarDefParser {
    /* VarDef Attributes */
    private Ident ident = null;
    private ArrayList<Token> leftBrackets = new ArrayList<>();
    private ArrayList<ConstExp> constExps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();
    /* Init Val */
    private Token eq = null;
    private InitVal initVal = null;
    /* VarDefEle */
    private VarDefEle varDefEle = null;
    private boolean isDefCorrect = true;

    /**
     *  VarDef → Ident { '[' ConstExp ']' } // 包含普通变量、一维数组、二维数组定义
     * | Ident { '[' ConstExp ']' } '=' InitVal
     * @return {@link VarDef}
     */
    public VarDef parseVarDef() {
        leftBrackets = new ArrayList<>();
        constExps = new ArrayList<>();
        rightBrackets = new ArrayList<>();
        ident = new IdentParser().parseIdent();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.LBRACK)) {
            leftBrackets.add(token);
            constExps.add(new ConstExpParser().parseConstExp());
            token = TLIterator.readNext();
            // TODO: 处理 k 类错误：缺失`]`
            handleKError(token);
            rightBrackets.add(token);
            token = TLIterator.readNext();
        }
        // System.out.println(token);
        if (token.getType().equals(LexType.ASSIGN)) { // '='
            eq = token;
            initVal = new InitValParser().parseInitVal();
            varDefEle = new VarDefInit(ident, leftBrackets, constExps, rightBrackets, eq, initVal);
        } else {
            // token now is ';', need to backspace
            TLIterator.unRead(1);
            varDefEle = new VarDefNull(ident, leftBrackets, constExps, rightBrackets);
        }
        // TODO: 添加新符号 & 处理 b 类错误：名字重定义
        addSymbol(ident, leftBrackets);
        return new VarDef(varDefEle);
    }

    /**
     * 添加新符号 & 处理 b 类错误
     * @param ident ident
     */
    private void addSymbol(Ident ident, ArrayList<Token> leftBrackets) {
        Symbol symbol;
        /* 判断维数 */
        if (leftBrackets.isEmpty()) {
            symbol = new SymbolVar(ident.getLineNum(), ident.getName(), SymbolType.VAR, 0);
        } else if (leftBrackets.size() == 1) {
            symbol = new SymbolVar1(ident.getLineNum(), ident.getName(), SymbolType.VAR1, 1);
        } else if (leftBrackets.size() == 2) {
            symbol = new SymbolVar2(ident.getLineNum(), ident.getName(), SymbolType.VAR2, 2);
        } else {
            symbol = new SymbolCon(Integer.MIN_VALUE, null, null, Integer.MIN_VALUE);
            System.out.println("ERROR in ConstDefParser!");
        }
        ident.setSymbol(symbol);
        if (STStack.isSymbolRedefined(symbol)) {
            // TODO: 处理 b 类错误：名字重定义
            handleBError(ident);
        } else {
            if (isDefCorrect) {
                STStack.getCurrentTable().addSymbol(symbol);
            }
        }
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
