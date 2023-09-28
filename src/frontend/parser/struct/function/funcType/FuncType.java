package frontend.parser.struct.function.funcType;

/**
 * FuncType 函数类型类
 * FuncType =  FuncTypeEle = 'void' | 'int'
 * @author SYH
 * @date 2023/09/26
 */
public class FuncType {
    private final String name = "<FuncType>";
    private FuncTypeEle funcTypeEle;

    public FuncType(FuncTypeEle funcTypeEle) {
        this.funcTypeEle = funcTypeEle;
    }
}
