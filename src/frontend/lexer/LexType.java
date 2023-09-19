package frontend.lexer;

import java.util.regex.Pattern;

/**
 * 单词类型枚举类
 * @author SYH
 * @date 2023/09/17
 */
public enum LexType {

    MAINTK(true, "main"),
    CONSTTK(true, "const"),
    INTTK(true, "int"),
    BREAKTK(true, "break"),
    CONTINUETK(true, "continue"),
    IFTK(true, "if"),
    ELSETK(true, "else"),
    FORTK(true, "for"),
    GETINTTK(true, "getint"),
    PRINTFTK(true, "printf"),
    RETURNTK(true, "return"),
    VOIDTK(true, "void"),

    /**
     * Ident
     * 匹配一个由字母、数字和下划线组成的标识符，
     * 其中第一个字符必须是字母或下划线
     */
    IDENFR(false, "[_A-Za-z][_A-Za-z0-9]*"),
    /**
     * IntConst
     * 匹配一个或多个数字
     */
    INTCON(false, "[0-9]+"),
    /**
     * FormatString
     * 匹配被双引号包围的任意字符串
     */
    STRCON(false, "\\\"[^\\\"]*\\\""),
    NEQ(false, "!="),
    EQL(false, "=="),
    LEQ(false, "<="),
    GEQ(false, ">="),
    AND(false, "&&"),
    NOT(false, "!"),

    OR(false, "\\|\\|"),

    PLUS(false, "\\+"),
    MINU(false, "-"),

    MULT(false, "\\*"),
    DIV(false, "/"),
    MOD(false, "%"),
    LSS(false, "<"),

    GRE(false, ">"),



    ASSIGN(false, "="),
    SEMICN(false, ";"),
    COMMA(false, ","),
    LPARENT(false, "\\("),
    RPARENT(false, "\\)"),
    LBRACK(false, "\\["),
    RBRACK(false, "]"),
    LBRACE(false, "\\{"),
    RBRACE(false, "}");

    /**
     * 是否贪婪匹配
     */
    private boolean isGreed;
    /**
     * 正则表达式字符串
     */
    private String patternString;
    /**
     * 正则表达式
     */
    private Pattern pattern;

    /**
     * 构造函数
     * @param isGreed 是否贪婪匹配
     * @param patternString 正则表达式字符串
     */

    LexType(boolean isGreed, String patternString) {
        this.isGreed = isGreed;
        this.patternString = patternString;
        String regex = "^" + patternString + (isGreed ? "(?![_A-Za-z0-9])" : "");
        this.pattern = Pattern.compile(regex);
    }

    /**
     * @return {@link Pattern}
     */
    public Pattern getPattern() {
        return this.pattern;
    }

    /**
     * @return boolean
     */
    public boolean getIsGreed() {
        return this.isGreed;
    }

    /**
     * @return {@link String}
     */
    public String getPatternString() {
        return this.patternString;
    }
}
