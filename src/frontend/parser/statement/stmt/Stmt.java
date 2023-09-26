package frontend.parser.statement.stmt;

/**
 * 语句
 * @author SYH
 * @date 2023/09/26
 */
public class Stmt {
    private final String name = "<Stmt>";
    private StmtEle stmtEle;

    public Stmt(StmtEle stmtEle) {
        this.stmtEle = stmtEle;
    }
}
