package frontend.parser.parser.declarationParser.constantParser;

import frontend.lexer.LexType;
import frontend.lexer.Token;
import frontend.parser.TLIterator;
import frontend.parser.struct.declaration.BType;

public class BTypeParser {
    /**
     * BType → 'int'
     * @return {@link BType}
     */
    public BType parseBtype() {
        Token first = TLIterator.readNextToken();
        if (!first.getType().equals(LexType.INTTK)) {
            System.out.println("ERROR: expect INTTK but not found in BTypeParser!");
        }
        return new BType(first);
    }
}
