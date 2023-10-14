package eumes;

public enum ErrorType {
    /**
     * 非法符号：
     * 格式字符串中出现非法字符，
     * 报错行号为所在行数
     */
    ILLEGAL_CHAR("a"),
    /**
     * 名字重定义：
     * 函数名或变量名在当前作用域下重复定义，
     * 报错行号为 Ident 所在行数
     */
    DUPLICATED_IDENT("b"),
    /**
     * 未定义的名字：
     * 包括函数未定义（在函数调用前没有函数声明）和 变量/常量未定义，
     * 报错行号为 Ident 所在行数
     */
    UNDEFINED_IDENT("c"),
    /**
     * 函数参数个数不匹配：
     * 函数调用语句中，参数个数与函数定义中的参数个数不匹配
     * 报错行号为函数名调用语句的函数名所在行数
     */
    MISMATCH_PARAM_NUM("d"),
    /**
     * 函数参数类型不匹配：
     * 函数调用语句中，参数个数与函数定义中的参数个数不匹配。
     * 报错行号为函数名调用语句的函数名所在行数
     */
    MISMATCH_PARAM_TYPE("e"),
    /**
     * 无返回值的函数存在不匹配的 return 语句：
     * 报错行号为 return 所在行号
     */
    RETURN_VALUE_VOID("f"),
    /**
     * 有返回值的函数缺少 return 语句：
     * 只需要考虑函数末尾是否存在 return 语句即可，无需考虑数据流
     */
    MISSING_RETURN("g"),
    /**
     * 改变常量的值：
     * LVal 为常量时，不能对其修改
     * 报错行号为 LVal 所在行号
     */
    ALTER_CONST("h"),
    /**
     * 缺少分号：
     * 报错行号为分号前一个非终结符所在行号
     */
    MISSING_SEMICN("i"),
    /**
     * 缺少右小括号：
     * 报错行号为右小括号前一个非终结符所在行号
     */
    MISSING_R_PARENT("j"),
    /**
     * 缺少右中括号：
     * 报错行号为右中括号前一个非终结符所在行号
     */
    MISSING_R_BACKET("k"),
    /**
     * print 中格式字符与表达式个数不匹配：
     * 报错行号为 printf 所在行号
     */
    MISMATCCH_PRINTF("l"),
    /**
     * 在非循环块中使用 break 和 continue 语句：
     * 报错行号为 break 和 continue 所在行号
     */
    MISUSE_END_LOOP("m");


    private String code;

    ErrorType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
