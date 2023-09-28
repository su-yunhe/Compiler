package frontend.parser.parser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenList;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.declarationParser.DeclParser;
import frontend.parser.parser.functionParser.FuncDefParser;
import frontend.parser.parser.functionParser.MainFuncDefParser;
import frontend.parser.struct.CompUnit;
import frontend.parser.struct.declaration.Decl;
import frontend.parser.struct.function.FuncDef;
import frontend.parser.struct.function.MainFuncDef;

import java.util.ArrayList;

public class CompUnitParser {
    private TokenList tokens;
    private TokenListIterator iterator;
    /* CompUnit params */
    private ArrayList<Decl> decls;
    private ArrayList<FuncDef> funcDefs;
    private MainFuncDef mainFuncDef;

    /* init CompUnitParser obj */
    private void initCompUnitParser() {
        this.iterator = new TokenListIterator(this.tokens);
    }

    public CompUnitParser(TokenList tokens) {
        this.tokens = tokens;
        initCompUnitParser();
        this.decls = new ArrayList<>();
        this.funcDefs = new ArrayList<>();
        this.mainFuncDef = null;
    }

    public CompUnit parseCompUnit() {
        this.decls = new ArrayList<>();
        this.funcDefs = new ArrayList<>();
        /* parse decls */
        parseDecls();
        /* parse FuncDefs */
        parseFuncDefs();
        /* parse MainFuncDef */
        parseMainFuncDef();

        return new CompUnit(this.decls, this.funcDefs, this.mainFuncDef);
    }


    /**
     * 解析一系列声明语句，并将解析结果存储在 decls 列表中。
     * 根据语法规则，每个声明语句可以是 const int 或者 int 标识符 的形式，直到遇到左括号为止。
     * 如果出现其他类型的声明语句，会退出解析过程。
     */
    private void parseDecls() {
        Token first = this.iterator.readNextToken();
        Token second = this.iterator.readNextToken();
        while (this.iterator.hasNext()) {
            Token third = this.iterator.readNextToken();
            if (third.getType().equals(LexType.LPARENT)) {
                this.iterator.unReadToken(3);
                return;
            } else {
                this.iterator.unReadToken(1);
            }
            if ((first.getType().equals(LexType.CONSTTK) &&
                    second.getType().equals(LexType.INTTK)) ||
                    (first.getType().equals(LexType.INTTK) &&
                            second.getType().equals(LexType.IDENFR))) {
                /* first -> const && second -> int */
                /* first -> int && second -> IDENFR */
                this.iterator.unReadToken(2);
                DeclParser declParser = new DeclParser(this.iterator);
                this.decls.add(declParser.parseDecl());
            } else {
                this.iterator.unReadToken(2);
                break;
            }
            first = this.iterator.readNextToken();
            second = this.iterator.readNextToken();
        }
    }

    /**
     * first 的类型是 INTTK 或 VOIDTK，second 的类型是 IDENFR
     */
    private void parseFuncDefs() {
        Token first = this.iterator.readNextToken();
        Token second = this.iterator.readNextToken();
        while (this.iterator.hasNext()) {
            if ((first.getType().equals(LexType.INTTK) ||
                    first.getType().equals(LexType.VOIDTK)) &&
                    second.getType().equals(LexType.IDENFR)) {
                /* first -> int/void && second -> IDENFR */
                this.iterator.unReadToken(2);
                FuncDefParser funcDefParser = new FuncDefParser(this.iterator);
                this.funcDefs.add(funcDefParser.parseFuncDef());
            } else {
                this.iterator.unReadToken(2);
                break;
            }
            first = this.iterator.readNextToken();
            second = this.iterator.readNextToken();
        }
    }

    /**
     * 一定进入
     */
    private void parseMainFuncDef() {
        MainFuncDefParser mainFuncDefParser = new MainFuncDefParser(this.iterator);
        this.mainFuncDef = mainFuncDefParser.parseMainFuncDef();
    }
}
