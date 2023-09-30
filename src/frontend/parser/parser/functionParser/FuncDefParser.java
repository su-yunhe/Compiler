package frontend.parser.parser.functionParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.statementParser.BlockParser;
import frontend.parser.parser.terminalParser.IdentParser;
import frontend.parser.struct.function.FuncDef;
import frontend.parser.struct.function.FuncFParams;
import frontend.parser.struct.function.funcType.FuncType;
import frontend.parser.struct.statement.Block;
import frontend.parser.struct.terminal.Ident;

public class FuncDefParser {
    /* FuncDef Attributes */
    private FuncType funcType = null;
    private Ident ident = null;
    private Token leftParent = null; // '('
    private FuncFParams funcFParams = null; // MAY exist
    private Token rightParent = null; // ')'
    private Block block = null;
    private FuncDef funcDef = null;
    
    /**
     * FuncDef → FuncType Ident '(' [FuncFParams] ')' Block
     * @return {@link FuncDef}
     */
    public FuncDef parseFuncDef() {
        /* FuncType */
        funcType = new FuncTypeParser().parseFuncType();
        /* Ident */
        ident = new IdentParser().parseIdent();
        /* ( */
        leftParent = TLIterator.readNextToken();
        rightParent = TLIterator.readNextToken();
        if (!rightParent.getType().equals(LexType.RPARENT)) {
            /* FuncFParams */
            TLIterator.unReadToken(1);
            funcFParams = new FuncFParamsParser().parseFuncFParams();
            /* ) */
            rightParent = TLIterator.readNextToken();
            /* Block */
            block = new BlockParser().parseBlock();
            funcDef = new FuncDef(funcType, ident, leftParent, funcFParams, rightParent, block);
        } else {
            /* Block */
            block = new BlockParser().parseBlock();
            funcDef = new FuncDef(funcType, ident, leftParent, rightParent, block);
        }
        return funcDef;
    }
}
