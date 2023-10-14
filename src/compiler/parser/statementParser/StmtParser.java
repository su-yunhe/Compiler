package compiler.parser.statementParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.statement.stmt.Stmt;
import struct.syntaxTree.statement.stmt.StmtEle;
import utils.ErrorUtils;
import struct.symbolTable.STStack;

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
        Token first = TLIterator.readNext();
        TLIterator.unRead(1);
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
                STStack.pushST();
                stmtEle = new BlockParser().parseBlock();
                STStack.popST();
                break;
            case LPARENT: case INTCON: case PLUS: case MINU:
                stmtEle = new StmtExpParser().parseStmtExp();
                break;
            default:
                System.out.println("ARRIVE UNEXPECTED DEFAULT BRANCH");
                // TODO:如果没有匹配到任何有效字符，说明当前应当为缺少分号的i类错误
                handleIError();
        }
        return new Stmt(stmtEle);
    }

    private void caseIdenfr(Token first) {
        int cnt = 0;
        Token token = first;
        while (!token.getType().equals(LexType.SEMICN) && TLIterator.hasNext()) {
            token = TLIterator.readNext();
            cnt++;
            if (token.getType().equals(LexType.ASSIGN)) {
                token = TLIterator.readNext();
                cnt++;
                if (token.getType().equals(LexType.GETINTTK)) {
                    TLIterator.unRead(cnt);
                    stmtEle = new StmtGetIntParser().parseStmtGetInt();
                } else {
                    TLIterator.unRead(cnt);
                    stmtEle = new StmtAssignParser().parseStmtAssign(); // LVal '=' Exp ';'
                }
                return;
            }
        }
        TLIterator.unRead(cnt);
        stmtEle = new StmtExpParser().parseStmtExp();
    }

    /**
     * 处理i类错误：缺失`;`
     */
    private void handleIError() {
        TLIterator.unRead(2);
        Token prev = TLIterator.readNext();
        ErrorUtils.handleI(prev);
    }
}
