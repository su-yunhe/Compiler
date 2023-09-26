package frontend.parser.declaration;

/**
 * 声明类
 *  Decl → DeclEle = [ConstDecl | VarDecl] 【常量声明/变量声明】
 * @author SYH
 * @date 2023/09/26
 */
public class Decl {
    private final String name = "<Decl>";
    private DeclEle declEle;

    public Decl(DeclEle declEle) {
        this.declEle = declEle;
    }
}
