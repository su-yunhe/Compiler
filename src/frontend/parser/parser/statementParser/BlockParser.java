package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.statement.Block;
import frontend.parser.struct.statement.BlockItem;

import java.util.ArrayList;

public class BlockParser {
    /* Block Attributes */
    private Token leftBrace = null;// '{'
    private ArrayList<BlockItem> blockItems = new ArrayList<>();
    private Token rightBrace = null; // '}'

    /**
     * Block → '{' { BlockItem } '}'
     * @return {@link Block}
     */
    public Block parseBlock() {
        /* '{' */
        leftBrace = TLIterator.readNextToken();
        BlockItemParser blockItemParser = new BlockItemParser();
        Token token = TLIterator.readNextToken();
        while (!token.getType().equals(LexType.RBRACE)) {
            /* { BlockItem } */
            TLIterator.unReadToken(1);
            blockItems.add(blockItemParser.parseBlockItem());
            token = TLIterator.readNextToken();
        }
        /* '}' */
        rightBrace = token;
        return new Block(leftBrace, blockItems, rightBrace);
    }
}
