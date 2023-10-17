package compiler.parser.declarationParser.constantParser;

import enums.LexType;
import struct.symbol.symbol.Symbol;
import struct.symbol.symbol.SymbolCon;
import struct.symbol.symbol.SymbolCon1;
import struct.symbol.symbol.SymbolCon2;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.expressionParser.ConstExpParser;
import compiler.parser.terminalParser.IdentParser;
import struct.syntaxTree.declaration.constant.ConstDef;
import struct.syntaxTree.declaration.constant.constInitVal.ConstInitVal;
import struct.syntaxTree.expression.ConstExp;
import struct.syntaxTree.terminal.Ident;
import utils.ErrorUtils;
import struct.symbolTable.STStack;
import enums.SymbolType;

import java.util.ArrayList;

public class ConstDefParser {
    private Ident ident;
    private ArrayList<Token> leftBrackets = new ArrayList<>();
    private ArrayList<ConstExp> constExps = new ArrayList<>();
    private ArrayList<Token> rightBrackets = new ArrayList<>();
    private Token eq = null; // =
    private ConstInitVal constInitVal = null;
    private boolean isDefCorrect = true;

    /**
     * ConstDef → Ident { '[' ConstExp ']' } '=' ConstInitVal
     * @return {@link ConstDef}
     */
    public ConstDef parseConstDef() {
        leftBrackets = new ArrayList<>();
        constExps = new ArrayList<>();
        rightBrackets = new ArrayList<>();

        ident = new IdentParser().parseIdent();
        Token token = TLIterator.readNext();
        while (token.getType().equals(LexType.LBRACK)) {
            /* '[' */
            leftBrackets.add(token);
            /* ConstExp */
            ConstExp constExp = new ConstExpParser().parseConstExp();
            constExps.add(constExp);
            token = TLIterator.readNext();
            /* ']' */
            // TODO: 处理 k 类错误：缺失`]`
            handleKError(token);
            rightBrackets.add(token);
            token = TLIterator.readNext();
        }
        /* = */
        if (!token.getType().equals(LexType.ASSIGN)) {
            System.out.println("EXPECT ASSIGN HERE");
        }
        eq = token;
        constInitVal = new ConstInitValParser().parseConstInitVal();
        // TODO: 添加新符号 & 处理 b 类错误：名字重定义
        addSymbolCon(ident, leftBrackets, constInitVal);
        return new ConstDef(ident, leftBrackets, constExps, rightBrackets, eq, constInitVal);
    }

    /**
     * 添加新符号 & 处理 b 类错误
     * @param ident ident
     */
    private void addSymbolCon(Ident ident, ArrayList<Token> leftBrackets, ConstInitVal constInitVal) {
        Symbol symbol;
        /* 判断维数 */
        if (leftBrackets.isEmpty()) {
            symbol = new SymbolCon(ident.getLineNum(), ident.getName(), SymbolType.CON, 0);
        } else if (leftBrackets.size() == 1) {
            symbol = new SymbolCon1(ident.getLineNum(), ident.getName(), SymbolType.CON1, 1);
        } else if (leftBrackets.size() == 2) {
            symbol = new SymbolCon2(ident.getLineNum(), ident.getName(), SymbolType.CON2, 2);
        } else {
            symbol = new SymbolCon(Integer.MIN_VALUE, null, null, Integer.MIN_VALUE);
            System.out.println("ERROR in ConstDefParser!");
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
