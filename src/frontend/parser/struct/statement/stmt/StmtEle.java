package frontend.parser.struct.statement.stmt;

/**
 * StmtEle 语句类文法基类接口
 * StmtEle =
 * LVal '=' Exp ';' // 每种类型的语句都要覆盖
 * | [Exp] ';' //有无Exp两种情况
 * | Block
 * | 'if' '(' Cond ')' Stmt [ 'else' Stmt ] // 1.有else 2.无else
 * | 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt // 1. 无缺省 2. 缺省第一个ForStmt 3. 缺省Cond 4. 缺省第二个ForStmt
 * | 'break' ';' | 'continue' ';'
 * | 'return' [Exp] ';' // 1.有Exp 2.无Exp
 * | LVal '=' 'getint''('')'';'
 * | 'printf''('FormatString{','Exp}')'';' // 1.有Exp 2.无Exp
 *
 * 被 StmtAssign, StmtExp, StmtNull, StmtBlock, StmtCond, StmtFor, StmtBreak,
 * StmtContinue, StmtReturn, StmtGetInt 和 StmtPrintf 继承。
 * @author SYH
 * @date 2023/09/26
 */
public interface StmtEle {

}
