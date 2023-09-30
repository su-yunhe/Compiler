package frontend.parser.parser.terminalParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.terminal.Ident;

public class IdentParser {
    /* Ident Attribute */
    private Token token; // ident
    public Ident parseIdent() {
        token = TLIterator.readNextToken();
        if (!token.getType().equals(LexType.IDENFR)) {
            System.out.println("EXPECT IDENFR HERE");
        }
        return new Ident(token);
    }
}
