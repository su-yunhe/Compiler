package frontend.parser.parser.terminalParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.terminal.IntConst;

public class IntConstParser {
    /* IntConst */
    private Token token = null;
    public IntConst parseIntConst() {
        this.token = TLIterator.readNextToken();
        if (!this.token.getType().equals(LexType.INTCON)) {
            System.out.println("EXPECT INTCON HERE");
        }
        return new IntConst(this.token);
    }
}
