package frontend.parser.parser.functionParser;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.statementParser.BlockParser;
import frontend.parser.struct.function.MainFuncDef;
import frontend.parser.struct.statement.Block;

public class MainFuncDefParser {
    private TokenListIterator iterator;
    /* MainFuncDef Attributes */
    private Token intTk; // 'int'
    private Token mainTk; // 'main'
    private Token leftParent; // '('
    private Token rightParent; // ')'
    private Block block;

    public MainFuncDefParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public MainFuncDef parseMainFuncDef() {
        this.intTk = this.iterator.readNextToken();
        this.mainTk = this.iterator.readNextToken();
        this.leftParent = this.iterator.readNextToken();
        this.rightParent = this.iterator.readNextToken();
        BlockParser blockParser = new BlockParser(this.iterator);
        this.block = blockParser.parseBlock();
        return new MainFuncDef(this.intTk, this.mainTk,
                this.leftParent, this.rightParent, this.block);
    }
}
