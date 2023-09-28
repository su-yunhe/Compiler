package frontend.parser.struct.statement;

import frontend.lexer.Token;
import frontend.parser.struct.statement.stmt.StmtEle;


import java.util.ArrayList;

/**
 * Block 语句块类
 * Block → '{' { BlockItem } '}' // 1.花括号内重复0次 2.花括号内重复多次
 * @author SYH
 * @date 2023/09/26
 */
public class Block implements StmtEle {
    private final String name = "<Block>";
    private Token leftBrace; // '{'
    private ArrayList<BlockItem> blockItems;
    private Token rightBrace; // '}'

    public Block(Token leftBrace,
                 ArrayList<BlockItem> blockItems,
                 Token rightBrace) {
        this.leftBrace = leftBrace;
        this.blockItems = blockItems;
        this.rightBrace = rightBrace;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.leftBrace.toString());
        if (blockItems != null && blockItems.size() != 0) {
            int len = blockItems.size();
            for (int i = 0; i < len; i++) {
                sb.append(this.blockItems.get(i).toString());
            }
        }
        sb.append(this.rightBrace.toString());
        sb.append(this.name).append("\n");
        return sb.toString();
    }
}
