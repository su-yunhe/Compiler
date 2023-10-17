package struct.syntaxTree.function.funcType;

import enums.LexType;

/**
 * 函数类型类文法基类接口
 * FuncTypeEle = 'void' | 'int'
 * 被 FuncVoid 和 FuncInt 类继承
 * @author SYH
 * @date 2023/09/26
 */
public interface FuncTypeEle {
    LexType getType();
}
