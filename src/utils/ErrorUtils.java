package utils;

import eumes.ErrorType;
import struct.error.Error;
import struct.error.ErrorTable;
import struct.syntaxTree.statement.Block;
import struct.syntaxTree.statement.stmt.StmtReturn;
import struct.token.Token;
import struct.syntaxTree.expression.primaryExp.LVal;
import struct.syntaxTree.terminal.FormatString;
import struct.syntaxTree.terminal.Ident;


public final class ErrorUtils {
    /**
     * 错误码：a <br/>
     * 错误描述：FormatString 中出现非法字符，即【十进制编码为32,33,40-126的ASCII字符，'\'（编码92）出现当且仅当为'\n'，%d】以外的字符。 <br/>
     * 报错信息：FormatString 所在的行数。
     * @param formatString formatString
     */
    public static void handleA(FormatString formatString) {
        String s = formatString.getContent();
        boolean isLegal = true;
        for (int i = 1; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if (!(c == 32 || c == 33 || (40 <= c && c <= 126))) {
                if (c == '%') {
                    if (!(i + 1 < s.length() - 1 && s.charAt(i + 1) == 'd')) {
                        isLegal = false;
                        break;
                    }
                }
                else {
                    isLegal = false;
                    break;
                }
            } else {
                if (c == '\\' && (i + 1 >= s.length() - 1 || s.charAt(i + 1) != 'n')) {
                    isLegal = false;
                    break;
                }
            }
        }
        if(!isLegal) {
            Error error = new Error(formatString.getLineNum(), ErrorType.ILLEGAL_CHAR);
            ErrorTable.addError(error);
            System.out.println("[Line " + formatString.getLineNum() + ", ERROR a]: Illegal FormatString at line " + formatString.getLineNum() + ", now is " + formatString);
        }
    }

    /**
     * 错误码：b <br/>
     * 错误描述：函数名或变量名在当前作用域下重复定义 <br/>
     * 报错信息：ident 所在行数
     * @param ident ident
     */
    public static void handleB(Ident ident) {
        Error error = new Error(ident.getLineNum(), ErrorType.DUPLICATED_IDENT);
        ErrorTable.addError(error);
        System.out.println("[Line " + ident.getLineNum() + ", ERROR b]: Symbol redefined at line " + ident.getLineNum() + ", ident is " + ident);
    }

    /**
     * 错误码：c <br/>
     * 错误描述：使用了未定义的标识符 <br/>
     * 报错信息：Ident 所在行数。
     * @param ident ident
     */
    public static void handleC(Ident ident) {
        Error error = new Error(ident.getLineNum(), ErrorType.UNDEFINED_IDENT);
        ErrorTable.addError(error);
        System.out.println("[Line " + ident.getLineNum() + ", ERROR c]: Symbol undefined at line " + ident.getLineNum() + ", ident is " + ident);
    }

    /**
     * 错误码：d <br/>
     * 错误描述：函数调用语句中，参数个数与函数定义中的参数个数不匹配 <br/>
     * 报错信息：函数调用语句的函数名所在行数
     * @param ident ident
     */
    public static void handleD(Ident ident) {
        Error error = new Error(ident.getLineNum(), ErrorType.MISMATCH_PARAM_NUM);
        ErrorTable.addError(error);
        System.out.println("[Line " + ident.getLineNum() + ", ERROR d]: Symbol redefined at line " + ident.getLineNum() + ", funcName is " + ident);
    }

    /**
     * 错误码：e <br/>
     * 错误描述：函数调用语句中，参数类型与函数定义中对应位置的参数类型不匹配。 <br/>
     * 报错信息：函数调用语句的函数名所在行数
     * @param ident ident
     */
    public static void handleE(Ident ident) {
        Error error = new Error(ident.getLineNum(), ErrorType.MISMATCH_PARAM_TYPE);
        ErrorTable.addError(error);
        System.out.println("[Line " + ident.getLineNum() + ", ERROR e]: Symbol redefined at line " + ident.getLineNum() + ", funcName is " + ident);
    }

    public static void handleF(StmtReturn stmtReturn) {
        Error error = new Error(stmtReturn.getLineNum(), ErrorType.RETURN_VALUE_VOID);
        ErrorTable.addError(error);
        System.out.println("[Line " + stmtReturn.getLineNum() + ", ERROR f]: Unexpect return at line " + stmtReturn.getLineNum());
    }

    public static void handleG(Block block) {
        Error error = new Error(block.getLineNum(), ErrorType.MISSING_RETURN);
        ErrorTable.addError(error);
        System.out.println("[Line " + block.getLineNum() + ", ERROR g]: Miss return at line " + block.getLineNum());
    }

    public static void handleH(LVal lVal) {
        Error error = new Error(lVal.getLineNum(), ErrorType.ALTER_CONST);
        ErrorTable.addError(error);
        System.out.println("[Line " + lVal.getLineNum() + ", ERROR h]: Assign to constant at line " + lVal.getLineNum());
    }



    /**
     * 错误码：i <br/>
     * 错误描述：文法中任何需要有 `;` 的部分缺失。 <br/>
     * 报错信息：分号前一个非终结符所在的行数。
     *
     * @param prev 前一个非终结符
     */
    public static void handleI(Token prev){
        Error error = new Error(prev.getLineNum(), ErrorType.MISSING_SEMICN);
        ErrorTable.addError(error);
        System.out.println("[Line " + prev.getLineNum() + ", ERROR i]: Except ';' at line " + prev.getLineNum() + ", prev now is " + prev);
    }

    /**
     * 错误码：j <br/>
     * 错误描述：文法中任何需要有`)`的部分缺失。 <br/>
     * 报错信息：右小括号前一个非终结符所在的行数。
     * @param prev 前一个非终结符
     */
    public static void handleJ(Token prev) {
        Error error = new Error(prev.getLineNum(), ErrorType.MISSING_R_PARENT);
        ErrorTable.addError(error);
        System.out.println("[Line " + prev.getLineNum() + ", ERROR j]: Except ')' at line " + prev.getLineNum() + ", prev now is " + prev);
    }

    /**
     * 错误码：k <br/>
     * 错误描述：文法中任何需要有`]`的部分缺失。 <br/>
     * 报错信息：右中括号前一个非终结符所在的行数。
     * @param prev 前一个终结符
     */
    public static void handleK(Token prev) {
        Error error = new Error(prev.getLineNum(), ErrorType.MISSING_R_BACKET);
        ErrorTable.addError(error);
        System.out.println("[Line " + prev.getLineNum() + ", ERROR k]: Except ']' at line " + prev.getLineNum() + ", prev now is " + prev);
    }

    public static void handleL(FormatString formatString) {
        Error error = new Error(formatString.getLineNum(), ErrorType.MISMATCCH_PRINTF);
        ErrorTable.addError(error);
        System.out.println("[Line " + formatString.getLineNum() + ", ERROR l]: The num of %d does not match at line " + formatString.getLineNum());
    }

    public static void handleM(Token endTk) {
        Error error = new Error(endTk.getLineNum(), ErrorType.MISUSE_END_LOOP);
        ErrorTable.addError(error);
        System.out.println("[Line " + endTk.getLineNum() + ", ERROR m]: MisUse endTk at line " + endTk.getLineNum());
    }
}
