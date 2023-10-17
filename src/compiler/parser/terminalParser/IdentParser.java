package compiler.parser.terminalParser;

import enums.LexType;
import struct.token.Token;
import utils.TLIterator;
import struct.syntaxTree.terminal.Ident;

public class IdentParser {
    /* Ident Attribute */
    private Token token; // ident
    public Ident parseIdent() {
        token = TLIterator.readNext();
        if (!token.getType().equals(LexType.IDENFR)) {
            System.out.println("EXPECT IDENFR HERE");
        }
        return new Ident(token);
    }
}
