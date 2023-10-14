package struct.syntaxTree.function;

import struct.token.Token;
import struct.syntaxTree.function.funcType.FuncType;
import struct.syntaxTree.statement.Block;
import struct.syntaxTree.terminal.Ident;

/**
 * FuncDef 函数定义类
 * FuncDef → FuncType Ident '(' [FuncFParams] ')' Block // 1.无形参 2.有形参
 * @author SYH
 * @date 2023/09/26
 */
public class FuncDef {
    private final String name = "<FuncDef>";
    private FuncType funcType;
    private Ident ident;
    private Token leftParent; // '('
    private FuncFParams funcFParams = null; // MAY exist
    private Token rightParent; // ')'
    private Block block;

    public FuncDef(FuncType funcType,
                   Ident ident,
                   Token leftParent,
                   Token rightParent,
                   Block block) {
        this.funcType = funcType;
        this.ident = ident;
        this.leftParent = leftParent;
        this.rightParent = rightParent;
        this.block = block;
    }

    public FuncDef(FuncType funcType,
                   Ident ident,
                   Token leftParent,
                   FuncFParams funcFParams,
                   Token rightParent,
                   Block block) {
        this(funcType, ident, leftParent, rightParent, block);
        this.funcFParams = funcFParams;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(funcType.toString());
        sb.append(ident.toString());
        sb.append(leftParent.toString());
        if (funcFParams != null) {
            sb.append(funcFParams.toString());
        }
        sb.append(rightParent.toString());
        sb.append(block.toString());
        sb.append(name).append("\n");
        return sb.toString();
    }
}
