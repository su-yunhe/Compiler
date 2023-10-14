package compiler.parser.statementParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import compiler.parser.declarationParser.DeclParser;
import struct.syntaxTree.statement.BlockItem;
import struct.syntaxTree.statement.BlockItemEle;

public class BlockItemParser {
    /* BlockItemEle */
    private BlockItemEle blockItemEle = null;
    /**
     * BlockItem → BlockItemEle = Decl | Stmt
     * @return {@link BlockItem}
     */
    public BlockItem parseBlockItem() {
        Token token = TLIterator.readNext();
        TLIterator.unRead(1);
        if (token.getType().equals(LexType.CONSTTK) ||
                token.getType().equals(LexType.INTTK)) {
            blockItemEle = new DeclParser().parseDecl();
        } else {
            blockItemEle = new StmtParser().parseStmt();
        }
        return new BlockItem(blockItemEle);
    }
}
