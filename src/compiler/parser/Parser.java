package compiler.parser;

import eumes.LexType;
import struct.token.Token;
import compiler.parser.declarationParser.DeclParser;
import compiler.parser.functionParser.FuncDefParser;
import compiler.parser.functionParser.MainFuncDefParser;
import struct.syntaxTree.CompUnit;
import struct.syntaxTree.declaration.Decl;
import struct.syntaxTree.function.FuncDef;
import struct.syntaxTree.function.MainFuncDef;
import struct.symbolTable.STStack;
import utils.TLIterator;

import java.util.ArrayList;

public class Parser {
    /* CompUnit params */
    private ArrayList<Decl> decls;
    private ArrayList<FuncDef> funcDefs;
    private MainFuncDef mainFuncDef;


    public Parser() {
        TLIterator.initTokenListIterator();
        /* 初始化栈式符号表 */
        STStack.initSTStack();
        decls = new ArrayList<>();
        funcDefs = new ArrayList<>();
        mainFuncDef = null;
    }

    public CompUnit parseCompUnit() {
        decls = new ArrayList<>();
        funcDefs = new ArrayList<>();
        /* parse decls */
        parseDecls();
        /* parse FuncDefs */
        parseFuncDefs();
        /* parse MainFuncDef */
        parseMainFuncDef();
        System.out.println(STStack.STStackToString());
        return new CompUnit(decls, funcDefs, mainFuncDef);
    }


    /**
     * 解析一系列声明语句，并将解析结果存储在 decls 列表中。
     * 根据语法规则，每个声明语句可以是 const int 或者 int 标识符 的形式，直到遇到左括号为止。
     * 如果出现其他类型的声明语句，会退出解析过程。
     */
    private void parseDecls() {
        Token first = TLIterator.readNext();
        Token second = TLIterator.readNext();
        while (TLIterator.hasNext()) {
            if ((first.getType().equals(LexType.CONSTTK) &&
                    second.getType().equals(LexType.INTTK)) ||
                    (first.getType().equals(LexType.INTTK) &&
                            second.getType().equals(LexType.IDENFR))) {
                /* const int || int IDENFR */
                Token third = TLIterator.readNext();
                if (third.getType().equals(LexType.LPARENT)) {
                    /* int main () */
                    TLIterator.unRead(3);
                    return;
                }
                TLIterator.unRead(3);
                DeclParser declParser = new DeclParser();
                this.decls.add(declParser.parseDecl());
            } else {
                TLIterator.unRead(2);
                return;
            }
            first = TLIterator.readNext();
            second = TLIterator.readNext();
        }
    }

    /**
     * first 的类型是 INTTK 或 VOIDTK，second 的类型是 IDENFR
     */
    private void parseFuncDefs() {
        Token first = TLIterator.readNext();
        Token second = TLIterator.readNext();
        while (TLIterator.hasNext()) {
            if ((first.getType().equals(LexType.INTTK) ||
                    first.getType().equals(LexType.VOIDTK)) &&
                    second.getType().equals(LexType.IDENFR)) {
                /* int IDENFR || void IDENFR */
                TLIterator.unRead(2);
                FuncDefParser funcDefParser = new FuncDefParser();
                funcDefs.add(funcDefParser.parseFuncDef());
            } else {
                TLIterator.unRead(2);
                return;
            }
            first = TLIterator.readNext();
            second = TLIterator.readNext();
        }
    }

    /**
     * 一定进入
     */
    private void parseMainFuncDef() {
        MainFuncDefParser mainFuncDefParser = new MainFuncDefParser();
        mainFuncDef = mainFuncDefParser.parseMainFuncDef();
    }
}
