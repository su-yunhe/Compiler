package frontend.parser.struct.declaration.variable.varDef;

/**
 * VarDef 变量定义类
 * VarDef → varDefEle = Ident { '[' ConstExp ']' } | Ident { '[' ConstExp ']' } '=' InitVal
 * @author SYH
 * @date 2023/09/26
 */
public class VarDef {
    private final String name = "<VarDef>";
    private VarDefEle varDefEle;

    public VarDef(VarDefEle varDefEle) {
        this.varDefEle = varDefEle;
    }
}
