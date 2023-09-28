package frontend.parser.parser.statementParser;

import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.parser.expressionParser.primaryExpParser.LValParser;
import frontend.parser.struct.expression.primaryExp.LVal;
import frontend.parser.struct.statement.stmt.StmtGetInt;

public class StmtGetIntParser {
    private TokenListIterator iterator;
    /* StmtGetint Attributes */
    private LVal lval = null;
    private Token eq = null; // '='
    private Token getint = null; // 'getint'
    private Token leftParent = null; // '('
    private Token rightParent = null; // ')'
    private Token semicn = null; // ';'

    public StmtGetIntParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * LVal '=' 'getint''('')'';'
     * @return {@link StmtGetInt}
     */
    public StmtGetInt parseStmtGetInt() {
        LValParser lvalParser = new LValParser(this.iterator);
        this.lval = lvalParser.parseLVal();
        this.eq = this.iterator.readNextToken();
        this.getint = this.iterator.readNextToken();
        this.leftParent = this.iterator.readNextToken();
        this.rightParent = this.iterator.readNextToken();
        this.semicn = this.iterator.readNextToken();
        return new StmtGetInt(this.lval, this.eq, this.getint,
                this.leftParent, this.rightParent, this.semicn);
    }
}
