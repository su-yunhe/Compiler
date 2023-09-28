package frontend.parser.parser.functionParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.FuncFParamsParser;
import frontend.parser.parser.statementParser.BlockParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.function.FuncDef;
import frontend.parser.struct.function.FuncFParams;
import frontend.parser.struct.function.funcType.FuncType;
import frontend.parser.struct.statement.Block;
import frontend.parser.struct.terminal.Ident;

public class FuncDefParser {
    private TokenListIterator iterator;
    /* FuncDef Attributes */
    private FuncType funcType = null;
    private Ident ident = null;
    private Token leftParent = null; // '('
    private FuncFParams funcFParams = null; // MAY exist
    private Token rightParent = null; // ')'
    private Block block = null;
    private FuncDef funcDef = null;

    public FuncDefParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * FuncDef → FuncType Ident '(' [FuncFParams] ')' Block
     * @return {@link FuncDef}
     */
    public FuncDef parseFuncDef() {
        /* FuncType */
        FuncTypeParser funcTypeParser = new FuncTypeParser(this.iterator);
        this.funcType = funcTypeParser.parseFuncType();
        /* Ident */
        IdentParser identParser = new IdentParser(this.iterator);
        this.ident = identParser.parseIdent();
        /* ( */
        this.leftParent = this.iterator.readNextToken();
        this.rightParent = this.iterator.readNextToken();
        if (!this.rightParent.getType().equals(LexType.RPARENT)) {
            /* FuncFParams */
            this.iterator.unReadToken(1);
            FuncFParamsParser funcFParamsParser = new FuncFParamsParser(this.iterator);
            this.funcFParams = funcFParamsParser.parseFuncFParams();
            /* ) */
            this.rightParent = this.iterator.readNextToken();
            /* Block */
            BlockParser blockParser = new BlockParser(this.iterator);
            this.block = blockParser.parseBlock();
            this.funcDef = new FuncDef(this.funcType, this.ident, this.leftParent,
                    this.funcFParams, this.rightParent, this.block);
        } else {
            /* Block */
            BlockParser blockParser = new BlockParser(this.iterator);
            this.block = blockParser.parseBlock();
            this.funcDef = new FuncDef(this.funcType, this.ident, this.leftParent,
                    this.rightParent, this.block);
        }
        return funcDef;
    }
}
