package frontend.parser.parser.terminalParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.parser.struct.terminal.Ident;

public class IdentParser {
    private TokenListIterator iterator;
    /* Ident Attribute */
    private Token token; // ident

    public IdentParser(TokenListIterator iterator) {
        this.iterator = iterator;
    }

    public Ident parseIdent() {
        token = this.iterator.readNextToken();
        if (!token.getType().equals(LexType.IDENFR)) {
            System.out.println("EXPECT IDENFR HERE");
        }
        return new Ident(token);
    }
}
