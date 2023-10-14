package struct.syntaxTree.declaration;

import eumes.ReturnType;
import struct.syntaxTree.statement.BlockItemEle;

/**
 * 声明类
 *  Decl → DeclEle = [ConstDecl | VarDecl] 【常量声明/变量声明】
 * @author SYH
 * @date 2023/09/26
 */
public class Decl implements BlockItemEle {
    private final String name = "<Decl>";
    private DeclEle declEle;

    public Decl(DeclEle declEle) {
        this.declEle = declEle;
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.NONE;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        /* not output BType according to assignment requirement */
        sb.append(this.declEle.toString());
        return sb.toString();
    }


}
