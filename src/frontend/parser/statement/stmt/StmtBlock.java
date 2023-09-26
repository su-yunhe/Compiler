package frontend.parser.statement.stmt;

import frontend.parser.statement.Block;

public class StmtBlock implements StmtEle{
    private Block block;

    public StmtBlock(Block block) {
        this.block = block;
    }
}
