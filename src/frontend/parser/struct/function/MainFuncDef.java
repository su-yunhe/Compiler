package frontend.parser.struct.function;

import frontend.lexer.Token;
import frontend.parser.struct.statement.Block;

/**
 * MainFuncDef 主函数定义类
 * MainFuncDef → 'int' 'main' '(' ')' Block // 存在main函数
 * @author SYH
 * @date 2023/09/26
 */
public class MainFuncDef {
    private final String name = "<MainFuncDef>";
    private Token intTk; // 'int'
    private Token mainTk; // 'main'
    private Token leftParent; // '('
    private Token rightParent; // ')'
    private Block block;

    public MainFuncDef(Token intTk,
                       Token mainTk,
                       Token leftParent,
                       Token rightParent,
                       Block block) {
        this.intTk = intTk;
        this.mainTk = mainTk;
        this.leftParent = leftParent;
        this.rightParent = rightParent;
        this.block = block;
    }
}
