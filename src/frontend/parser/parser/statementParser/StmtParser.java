package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.statement.stmt.Stmt;
import frontend.parser.struct.statement.stmt.StmtEle;

public class StmtParser {
    /* Stmt Attributes */
    private StmtEle stmtEle = null;

    /**
     * Stmt → StmtEle =
     * LVal '=' Exp ';' // 每种类型的语句都要覆盖
     * | [Exp] ';' //有无Exp两种情况
     * | Block
     * | 'if' '(' Cond ')' Stmt [ 'else' Stmt ] // 1.有else 2.无else
     * | 'for' '(' [ForStmt] ';' [Cond] ';' [ForStmt] ')' Stmt // 1. 无缺省 2. 缺省第一个ForStmt 3. 缺省Cond 4. 缺省第二个ForStmt
     * | 'break' ';'
     * | 'continue' ';'
     * | 'return' [Exp] ';' // 1.有Exp 2.无Exp
     * | LVal '=' 'getint''('')'';'
     * | 'printf''('FormatString{','Exp}')'';' // 1.有Exp 2.无Exp
     * @return {@link Stmt}
     */
    public Stmt parseStmt() {
        Token first = TLIterator.readNextToken();
        TLIterator.unReadToken(1);
        switch (first.getType()) {
            case IFTK:
                /* if 语句*/
                stmtEle = new StmtCondParser().parseStmtCond();
                break;
            case FORTK:
                /* for 语句 */
                stmtEle = new StmtForParser().parseStmtFor();
                break;
            case BREAKTK:
                /* break 语句 */
                stmtEle = new StmtBreakParser().parseStmtBreak();
                break;
            case CONTINUETK:
                /* continue 语句 */
                stmtEle = new StmtContinueParser().parseStmtContinue();
                break;
            case RETURNTK:
                /* return 语句 */
                stmtEle = new StmtReturnParser().parseStmtReturn();
                break;
            case PRINTFTK:
                /* printf 语句 */
                stmtEle = new StmtPrintfParser().parseStmtPrint();
                break;
            case SEMICN:
                /* StmtNull 语句 */
                stmtEle = new StmtNullParser().pasreStmtNull();
                break;
            case IDENFR:
                caseIdenfr(first);
                break;
            case LBRACE: // '{'
                stmtEle = new BlockParser().parseBlock();
                break;
            case LPARENT: case INTCON: case PLUS: case MINU:
                stmtEle = new StmtExpParser().parseStmtExp();
                break;
            default:
                System.out.println("ARRIVE UNEXPECTED DEFAULT BRANCH");
        }
        return new Stmt(stmtEle);
    }

    private void caseIdenfr(Token first) {
        int cnt = 0;
        int mode = 0; // 0:assign 1:input
        boolean flag = false; // LVal = Exp; || LVal = getint();
        Token token = first;
        while (!token.getType().equals(LexType.SEMICN)) {
            token = TLIterator.readNextToken();
            cnt += 1;
            if (token.getType().equals(LexType.ASSIGN)) {
                flag = true;
            }
            if (token.getType().equals(LexType.GETINTTK)) {
                mode = 1;
            }
        }
        TLIterator.unReadToken(cnt);
        if (flag) {
            if (mode == 0) {
                stmtEle = new StmtAssignParser().parseStmtAssign(); // LVal '=' Exp ';'
            } else {
                stmtEle = new StmtGetIntParser().parseStmtGetInt();
            }
        } else {
            this.stmtEle = new StmtExpParser().parseStmtExp();
        }
    }
}
