package compiler.parser.statementParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.statement.Block;
import struct.syntaxTree.statement.BlockItem;

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
        blockItems = new ArrayList<>();

        /* '{' */
        leftBrace = TLIterator.readNext();
        Token token = TLIterator.readNext();
        while (!token.getType().equals(LexType.RBRACE)) {
            /* { BlockItem } */
            TLIterator.unRead(1);
            blockItems.add(new BlockItemParser().parseBlockItem());
            token = TLIterator.readNext();
        }
        /* '}' */
        rightBrace = token;
        return new Block(leftBrace, blockItems, rightBrace);
    }
}
