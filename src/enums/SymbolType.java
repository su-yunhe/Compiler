package enums;

/**
 * 符号类型枚举类
 * 符号包括 7 种：
 * 普通常量 CON、一维数组常量 CON1、二维数组常量 CON2、
 * 普通变量 VAR、一维数组变量 VAR1、二维数组变量 VAR2
 * 和函数名 FUNC
 * @author SYH
 * @date 2023/10/11
 */
public enum SymbolType {
    /**
     * 普通变量
     */
    VAR,
    /**
     * 一维数组变量
     */
    VAR1,
    /**
     * 二维数组变量
     */
    VAR2,
    /**
     * 普通常量
     */
    CON,
    /**
     * 一维数组常量
     */
    CON1,
    /**
     * 二维数组常量
     */
    CON2,
    /**
     * 函数
     */
    FUNC
}
