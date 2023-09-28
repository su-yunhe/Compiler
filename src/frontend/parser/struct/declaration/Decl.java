package frontend.parser.struct.declaration;

import frontend.parser.struct.statement.BlockItemEle;

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
}
