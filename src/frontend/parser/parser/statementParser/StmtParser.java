package frontend.parser.parser.statementParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.statement.stmt.Stmt;
import frontend.parser.struct.statement.stmt.StmtEle;

public class StmtParser {
    private TokenListIterator iterator;
    /* Stmt Attributes */
    private StmtEle stmtEle = null;

    public StmtParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public Stmt parseStmt() {
        Token first = this.iterator.readNextToken();
        switch (first.getType()) {
            case IFTK: // 'if'
                this.iterator.unReadToken(1);
                StmtCondParser stmtCondParser = new StmtCondParser(this.iterator);
                this.stmtEle = stmtCondParser.parseStmtCond();
                break;
            case FORTK: // 'for' -------------------------------------------
                this.iterator.unReadToken(1);
                StmtForParser stmtForParser = new StmtForParser(this.iterator);
                this.stmtEle = stmtForParser.parseStmtFor();
                break;
            case BREAKTK: // 'break'
                this.iterator.unReadToken(1);
                StmtBreakParser stmtBreakParser = new StmtBreakParser(this.iterator);
                this.stmtEle = stmtBreakParser.parseStmtBreak();
                break;
            case CONTINUETK: // 'continue'
                this.iterator.unReadToken(1);
                StmtContinueParser stmtContinueParser = new StmtContinueParser(this.iterator);
                this.stmtEle = stmtContinueParser.parseStmtContinue();
                break;
            case RETURNTK: // 'return'
                this.iterator.unReadToken(1);
                StmtReturnParser stmtReturnParser = new StmtReturnParser(this.iterator);
                this.stmtEle = stmtReturnParser.parseStmtReturn();
                break;
            case PRINTFTK: // 'printf'
                this.iterator.unReadToken(1);
                StmtPrintfParser stmtPrintfParser = new StmtPrintfParser(this.iterator);
                this.stmtEle = stmtPrintfParser.parseStmtPrint();
                break;
            case SEMICN: // ';'
                this.iterator.unReadToken(1);
                StmtNullParser stmtNullParser = new StmtNullParser(this.iterator);
                this.stmtEle = stmtNullParser.pasreStmtNull();
                break;
            /* TODO : handle exp and lval*/
            case IDENFR:
                caseIdenfr(first);
                break;
            case LBRACE: // '{'
                this.iterator.unReadToken(1);
                BlockParser blockParser = new BlockParser(this.iterator);
                this.stmtEle = blockParser.parseBlock();
                break;
            case LPARENT: case INTCON: case PLUS: case MINU:
                this.iterator.unReadToken(1);
                StmtExpParser stmtExpParser = new StmtExpParser(this.iterator);
                this.stmtEle = stmtExpParser.parseStmtExp();
                break;
            default:
                System.out.println("ARRIVE UNEXPECTED DEFAULT BRANCH");
        }
        Stmt stmt = new Stmt(this.stmtEle);
        return stmt;
    }

    private void caseIdenfr(Token first) {
        /* need to distinguish LVal = Exp, LVal = getint and [Exp] */
        int cnt = 1;
        int mode = 0; // 0:assign 1:input
        boolean flag = false; // LVal = Exp; || LVal = getint();
        Token token = first;
        while (!token.getType().equals(LexType.SEMICN)) {
            token = this.iterator.readNextToken();
            cnt += 1;
            if (token.getType().equals(LexType.ASSIGN)) {
                flag = true;
            }
            if (token.getType().equals(LexType.GETINTTK)) {
                mode = 1;
            }
        }
        this.iterator.unReadToken(cnt);
        if (flag) {
            if (mode == 0) {
                StmtAssignParser stmtAssignParser = new StmtAssignParser(this.iterator);
                this.stmtEle = stmtAssignParser.parseStmtAssign();
            } else if (mode == 1) {
                StmtGetIntParser stmtGetIntParser = new StmtGetIntParser(this.iterator);
                this.stmtEle = stmtGetIntParser.parseStmtGetInt();
            } else {
                System.out.println("REACHED UNEXPECTED BRANCH");
            }
        } else {
            StmtExpParser stmtExpParser = new StmtExpParser(this.iterator);
            this.stmtEle = stmtExpParser.parseStmtExp();
        }
    }
}
