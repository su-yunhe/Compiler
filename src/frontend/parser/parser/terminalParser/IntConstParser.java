package frontend.parser.parser.terminalParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.terminal.IntConst;

public class IntConstParser {
    private TokenListIterator iterator;
    /* IntConst */
    private Token token = null;

    public IntConstParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public IntConst parseIntConst() {
        this.token = this.iterator.readNextToken();
        if (!this.token.getType().equals(LexType.INTCON)) {
            System.out.println("EXPECT INTCON HERE");
        }
        return new IntConst(this.token);
    }
}
