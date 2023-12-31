package struct.syntaxTree.declaration;

import struct.token.Token;

/**
 * BType 基本类型类
 * BType → 'int' // 存在即可
 * @author SYH
 * @date 2023/09/26
 */
public class BType {
    private final String name = "<BType>";
    private Token token; // must be INTTK

    public BType(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(token.toString());
        /* not output BType according to assignment requirement */
        return sb.toString();
    }
}
