package frontend.parser.struct.statement.stmt;

import frontend.parser.struct.statement.Block;

/**
 * StmtBlock 语句块语句类
 * StmtBlock = Block
 * @author SYH
 * @date 2023/09/26
 */
public class StmtBlock implements StmtEle{
    private Block block;

    public StmtBlock(Block block) {
        this.block = block;
    }
}
