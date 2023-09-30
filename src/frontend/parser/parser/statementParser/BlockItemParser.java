package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.parser.declarationParser.DeclParser;
import frontend.parser.struct.statement.BlockItem;
import frontend.parser.struct.statement.BlockItemEle;

public class BlockItemParser {
    /* BlockItemEle */
    private BlockItemEle blockItemEle = null;
    /**
     * BlockItem → BlockItemEle = Decl | Stmt
     * @return {@link BlockItem}
     */
    public BlockItem parseBlockItem() {
        Token token = TLIterator.readNextToken();
        TLIterator.unReadToken(1);
        if (token.getType().equals(LexType.CONSTTK) ||
                token.getType().equals(LexType.INTTK)) {
            blockItemEle = new DeclParser().parseDecl();
        } else {
            blockItemEle = new StmtParser().parseStmt();
        }
        return new BlockItem(blockItemEle);
    }
}
