package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.declarationParser.DeclParser;
import frontend.parser.struct.statement.BlockItem;
import frontend.parser.struct.statement.BlockItemEle;

public class BlockItemParser {
    private TokenListIterator iterator;
    /* BlockItemEle */
    private BlockItemEle blockItemEle = null;

    public BlockItemParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * BlockItem → BlockItemEle = Decl | Stmt
     * @return {@link BlockItem}
     */
    public BlockItem parseBlockItem() {
        Token token = this.iterator.readNextToken();
        if (token.getType().equals(LexType.CONSTTK) ||
                token.getType().equals(LexType.INTTK)) {
            this.iterator.unReadToken(1);
            DeclParser declParser = new DeclParser(this.iterator);
            this.blockItemEle = declParser.parseDecl();
        } else {
            this.iterator.unReadToken(1);
            StmtParser stmtParser = new StmtParser(this.iterator);
            this.blockItemEle = stmtParser.parseStmt();
        }
        return new BlockItem(this.blockItemEle);
    }
}
