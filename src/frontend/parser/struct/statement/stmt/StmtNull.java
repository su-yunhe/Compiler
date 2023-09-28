package frontend.parser.struct.statement.stmt;

import frontend.lexer.Token;

/**
 * StmtNull 空表达式语句类
 * StmtNull = ;
 * @author SYH
 * @date 2023/09/26
 */
public class StmtNull implements StmtEle{
    private Token semicn;

    public StmtNull(Token semicn) {
        this.semicn = semicn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.semicn.toString());
        return sb.toString();
    }
}
