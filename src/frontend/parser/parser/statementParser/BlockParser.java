package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.statement.Block;
import frontend.parser.struct.statement.BlockItem;

import java.util.ArrayList;

public class BlockParser {
    private TokenListIterator iterator;
    /* Block Attributes */
    private Token leftBrace = null;// '{'
    private ArrayList<BlockItem> blockItems = new ArrayList<>();
    private Token rightBrace = null; // '}'

    public BlockParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * Block → '{' { BlockItem } '}'
     * @return {@link Block}
     */
    public Block parseBlock() {
        /* '{' */
        this.leftBrace = this.iterator.readNextToken();
        BlockItemParser blockItemParser = new BlockItemParser(this.iterator);
        Token token = this.iterator.readNextToken();
        while (!token.getType().equals(LexType.RBRACE)) {
            /* { BlockItem } */
            this.iterator.unReadToken(1);
            this.blockItems.add(blockItemParser.parseBlockItem());
            token = this.iterator.readNextToken();
        }
        /* '}' */
        this.rightBrace = token;
        return new Block(this.leftBrace, this.blockItems, this.rightBrace);
    }
}
