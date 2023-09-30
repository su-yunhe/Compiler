package frontend.parser.parser.functionParser;

import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.statementParser.BlockParser;
import frontend.parser.struct.function.MainFuncDef;
import frontend.parser.struct.statement.Block;

public class MainFuncDefParser {
    /* MainFuncDef Attributes */
    private Token intTk; // 'int'
    private Token mainTk; // 'main'
    private Token leftParent; // '('
    private Token rightParent; // ')'
    private Block block;
    public MainFuncDef parseMainFuncDef() {
        intTk = TLIterator.readNextToken();
        mainTk = TLIterator.readNextToken();
        leftParent = TLIterator.readNextToken();
        rightParent = TLIterator.readNextToken();
        block = new BlockParser().parseBlock();
        return new MainFuncDef(intTk, mainTk, leftParent, rightParent, block);
    }
}
