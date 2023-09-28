package frontend.parser.struct.statement;

/**
 * BlockItem 语句块类
 * BlockItem → BlockItemEle = Decl | Stmt // 覆盖两种语句块项
 * @author SYH
 * @date 2023/09/26
 */
public class BlockItem {
    private final String name = "<BlockItem>";
    private BlockItemEle blockItemEle;
    public BlockItem(BlockItemEle blockItemEle) {
        this.blockItemEle = blockItemEle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.blockItemEle.toString());
        return sb.toString();
    }
}
