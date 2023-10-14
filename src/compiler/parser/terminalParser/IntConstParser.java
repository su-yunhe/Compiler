package compiler.parser.terminalParser;

import eumes.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.terminal.IntConst;

public class IntConstParser {
    /* IntConst */
    private Token token = null;
    public IntConst parseIntConst() {
        this.token = TLIterator.readNext();
        if (!this.token.getType().equals(LexType.INTCON)) {
            System.out.println("EXPECT INTCON HERE");
        }
        return new IntConst(this.token);
    }
}
